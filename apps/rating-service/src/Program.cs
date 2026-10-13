using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using MongoDB.Driver;
using RabbitMQ.Client;
using Refit;
using Segundum.Adapters;
using Segundum.Clients;
using Segundum.Dtos;
using Segundum.Endpoints;
using Segundum.Middleware;
using Segundum.Ports;
using Segundum.Repositories;
using Segundum.Services;

var builder = WebApplication.CreateBuilder(args);

// OpenAPI. Swagger is a development-only feature: the document and the UI
// are registered only when Swagger:Enabled is true (Swagger__Enabled in the
// environment), which compose.dev.yaml sets and production leaves off, so
// /api/swagger-ui returns 404 in production.
var swaggerEnabled = builder.Configuration.GetValue<bool>("Swagger:Enabled");

// OpenAPI
builder.Services.AddEndpointsApiExplorer();
if (swaggerEnabled)
{
    builder.Services.AddOpenApiDocument(config =>
    {
        config.DocumentName = "RatingsAPI";
        config.Title = "SegundUM - Rating Service API";
        config.Description = "REST API for managing ratings of sellers and purchasers in the marketplace";
        config.Version = "1.0.0";
    });
}

// MongoDB. The name of the database is read from the connection string, so
// the URI is the single source of truth: changing it in .env is enough to
// point the service to another database, instead of the value of an
// appsettings key silently winning over the URI.
var mongoUri = builder.Configuration["MongoDB:Uri"]
        ?? throw new InvalidOperationException("MongoDB:Uri is not configured (set MongoDB__Uri)");
var mongoClient = new MongoClient(mongoUri);
var mongoDatabaseName = MongoUrl.Create(mongoUri).DatabaseName ?? "ratings";
builder.Services.AddSingleton<IMongoClient>(mongoClient);
builder.Services.AddSingleton<IMongoDatabase>(mongoClient.GetDatabase(mongoDatabaseName));
builder.Services.AddScoped<IRatingRepository, MongoRatingRepository>();

// RabbitMQ
var factory = new ConnectionFactory
{
    Uri = new Uri(builder.Configuration["RabbitMQ:ConnectionString"]!)
};
var connection = await factory.CreateConnectionAsync();
builder.Services.AddSingleton<IConnection>(connection);
builder.Services.AddSingleton<IEventPublisher, RabbitMQEventPublisher>();

// Sales microservice
builder.Services.AddRefitClient<ISaleApiClient>().ConfigureHttpClient(c =>
{
    c.BaseAddress = new Uri(builder.Configuration["SaleService:BaseUrl"]!);
});
builder.Services.AddScoped<ISalePartiesReader, RefitSalePartiesReader>();

builder.Services.AddScoped<IRatingService, RatingService>();

// Authentication. The platform issues one JWT in the API gateway, signed in
// jjwt style: HS256 over the base64 decoding of the shared secret. The token
// can travel in the Authorization header or in the access_token cookie, read
// it the same way the Java filters of the other services read it.
var jwtSecret = builder.Configuration["JWT_SECRET"]
        ?? throw new InvalidOperationException("JWT_SECRET is not configured");
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
        .AddJwtBearer(options =>
        {
            options.MapInboundClaims = false;
            options.TokenValidationParameters = new TokenValidationParameters
            {
                ValidateIssuer = false,
                ValidateAudience = false,
                ValidateLifetime = true,
                ClockSkew = TimeSpan.Zero,
                ValidAlgorithms = new[] { SecurityAlgorithms.HmacSha256 },
                IssuerSigningKey = new SymmetricSecurityKey(Convert.FromBase64String(jwtSecret))
            };
            options.Events = new JwtBearerEvents
            {
                OnMessageReceived = context =>
                {
                    if (string.IsNullOrEmpty(context.HttpContext.Request.Headers.Authorization)
                            && context.HttpContext.Request.Cookies.ContainsKey("access_token"))
                    {
                        context.Token = context.HttpContext.Request.Cookies["access_token"];
                    }
                    return Task.CompletedTask;
                },
                OnChallenge = async context =>
                {
                    // The missing or invalid token is answered with the same
                    // JSON body as the other services of the platform, instead
                    // of the empty challenge of the default handler.
                    context.HandleResponse();
                    context.Response.StatusCode = StatusCodes.Status401Unauthorized;
                    context.Response.ContentType = "application/json";
                    await context.Response.WriteAsJsonAsync(
                            ErrorResponse.From(StatusCodes.Status401Unauthorized,
                                    "Missing or invalid access token"));
                },
                OnForbidden = async context =>
                {
                    context.Response.StatusCode = StatusCodes.Status403Forbidden;
                    context.Response.ContentType = "application/json";
                    await context.Response.WriteAsJsonAsync(
                            ErrorResponse.From(StatusCodes.Status403Forbidden, "Access denied"));
                }
            };
        });

// Authorization. A rating can only be registered by a user, mirroring the
// hasAuthority('USER') of the Java services. The roles claim of the token is a
// single comma separated value.
builder.Services.AddAuthorization(options =>
{
    options.AddPolicy("User", policy => policy.RequireAssertion(context =>
    {
        var roles = context.User.FindFirst("roles")?.Value;
        return roles != null && roles.Split(',')
                .Select(role => role.Trim())
                .Contains("USER");
    }));
});

var app = builder.Build();

// Indexes of the ratings collection
await MongoRatingRepository.EnsureIndexesAsync(
        mongoClient,
        mongoDatabaseName,
        builder.Configuration["MongoDB:Collection"] ?? "ratings");

// OpenAPI user interface, registered only when Swagger:Enabled is true
// (see the flag read at the top of this file). When it is on, the endpoints
// hang from /api/ratings and the UI from /api/swagger-ui, the swagger entry
// link shared by every service of the platform. The UI and document paths
// have to be moved together or the page loads but cannot fetch the document.
if (swaggerEnabled)
{
    app.UseOpenApi(config =>
    {
        config.Path = "/api/swagger-ui/{documentName}/swagger.json";
    });
    app.UseSwaggerUi(config =>
    {
        config.DocumentTitle = "SegundUM - Rating Service API";
        config.Path = "/api/swagger-ui";
        config.DocumentPath = "/api/swagger-ui/{documentName}/swagger.json";
        config.DocExpansion = "list";
    });
}

app.UseMiddleware<ExceptionMiddleware>();
app.UseAuthentication();
app.UseAuthorization();
app.MapRatingsEndpoints();

app.Run();

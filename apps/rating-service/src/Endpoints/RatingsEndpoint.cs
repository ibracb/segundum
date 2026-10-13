using Microsoft.AspNetCore.Mvc;
using Segundum.Common;
using Segundum.Dtos;
using Segundum.Models;
using Segundum.Services;

namespace Segundum.Endpoints;

/// <summary>
/// Minimal API of the ratings microservice.
/// </summary>
public static class RatingsEndpoints
{
    public static void MapRatingsEndpoints(this WebApplication app)
    {
        var group = app.MapGroup("/api/ratings").RequireAuthorization();

        group.MapPost("/seller", RateSeller)
            .RequireAuthorization("User")
            .WithName("CreateRatingForSeller")
            .Produces(StatusCodes.Status201Created)
            .Produces<ErrorResponse>(StatusCodes.Status400BadRequest)
            .Produces<ErrorResponse>(StatusCodes.Status404NotFound)
            .Produces<ErrorResponse>(StatusCodes.Status409Conflict);

        group.MapPost("/purchaser", RatePurchaser)
            .RequireAuthorization("User")
            .WithName("CreateRatingForPurchaser")
            .Produces(StatusCodes.Status201Created)
            .Produces<ErrorResponse>(StatusCodes.Status400BadRequest)
            .Produces<ErrorResponse>(StatusCodes.Status404NotFound)
            .Produces<ErrorResponse>(StatusCodes.Status409Conflict);

        group.MapGet("/{id}", GetById)
            .WithName("GetRating")
            .Produces<RatingResponse>(StatusCodes.Status200OK)
            .Produces(StatusCodes.Status404NotFound);

        group.MapGet("/seller/{sellerId}", GetAsSeller)
            .WithName("GetRatingsAsSeller")
            .Produces<RatingListResponse>(StatusCodes.Status200OK);

        group.MapGet("/purchaser/{purchaserId}", GetAsPurchaser)
            .WithName("GetRatingsAsPurchaser")
            .Produces<RatingListResponse>(StatusCodes.Status200OK);
    }

    private static async Task<IResult> RateSeller(
            [FromBody] RateSellerRequest request, IRatingService service, HttpContext context)
    {
        if (!IsAuthenticatedRater(context, request.PurchaserId))
            return NotTheRater();

        var result = await service.RateSellerAsync(request);

        if (!result.Success)
            return Failure(result);

        return Results.Created(AbsoluteUrl(context, $"/api/ratings/{result.Value!.Id}"), null);
    }

    private static async Task<IResult> RatePurchaser(
            [FromBody] RatePurchaserRequest request, IRatingService service, HttpContext context)
    {
        if (!IsAuthenticatedRater(context, request.SellerId))
            return NotTheRater();

        var result = await service.RatePurchaserAsync(request);

        if (!result.Success)
            return Failure(result);

        return Results.Created(AbsoluteUrl(context, $"/api/ratings/{result.Value!.Id}"), null);
    }

    private static async Task<IResult> GetById(string id, IRatingService service, HttpContext context)
    {
        var rating = await service.GetAsync(id);

        return rating == null ? Results.NotFound() : Results.Ok(ToRatingResponse(rating, context));
    }

    private static async Task<IResult> GetAsSeller(
            string sellerId, IRatingService service, IConfiguration configuration, HttpContext context)
    {
        var summaries = await service.GetSummariesAsSellerAsync(sellerId);

        return Results.Ok(ToRatingListResponse(summaries, RatedUserRole.Seller,
                $"/api/ratings/seller/{sellerId}", sellerId, configuration, context));
    }

    private static async Task<IResult> GetAsPurchaser(
            string purchaserId, IRatingService service, IConfiguration configuration, HttpContext context)
    {
        var summaries = await service.GetSummariesAsPurchaserAsync(purchaserId);

        return Results.Ok(ToRatingListResponse(summaries, RatedUserRole.Purchaser,
                $"/api/ratings/purchaser/{purchaserId}", purchaserId, configuration, context));
    }

    /// <summary>
    /// Builds the error body of a failed operation, with the status code the
    /// service has decided for it.
    /// </summary>
    private static IResult Failure(Result result) =>
        Results.Json(ErrorResponse.From(result.StatusCode, result.Message!),
                statusCode: result.StatusCode);

    /// <summary>
    /// True when the subject of the token is the user who gives the rating: the
    /// same check the other services perform in their @PreAuthorize rules
    /// against the principal of the request.
    /// </summary>
    private static bool IsAuthenticatedRater(HttpContext context, string raterId) =>
        string.Equals(context.User.FindFirst("sub")?.Value, raterId, StringComparison.Ordinal);

    /// <summary>
    /// Rejection of a rating whose declared author is not the authenticated
    /// user of the token.
    /// </summary>
    private static IResult NotTheRater() =>
        Results.Json(ErrorResponse.From(StatusCodes.Status403Forbidden,
                        "The authenticated user is not the author of the rating"),
                statusCode: StatusCodes.Status403Forbidden);

    private static RatingResponse ToRatingResponse(Rating rating, HttpContext context) =>
        new(rating.Id!,
            rating.SaleId,
            rating.RaterId,
            rating.RatedUserId,
            rating.RatedUserRole.ToString(),
            rating.Score,
            rating.Comment,
            [new LinkResponse("self", AbsoluteUrl(context, $"/api/ratings/{rating.Id}"))]);

    /// <summary>
    /// Builds a listing from the summaries of the service. The rated user and
    /// the role are the ones this endpoint is about: the user of the route and
    /// the role of the endpoint, because the summaries only carry what varies
    /// from rating to rating.
    /// </summary>
    private static RatingListResponse ToRatingListResponse(List<RatingSummary> summaries, RatedUserRole role,
            string path, string userId, IConfiguration configuration, HttpContext context)
    {
        var items = summaries
                .Select(summary => new RatingResponse(
                        summary.Id,
                        summary.SaleId,
                        summary.RaterId,
                        userId,
                        role.ToString(),
                        summary.Score,
                        summary.Comment,
                        [new LinkResponse("self", AbsoluteUrl(context, $"/api/ratings/{summary.Id}"))]))
                .ToList();

        var links = new List<LinkResponse>
        {
            new("self", AbsoluteUrl(context, path))
        };

        var userUrl = UserUrl(configuration, userId);
        if (!string.IsNullOrWhiteSpace(userUrl))
            links.Add(new LinkResponse("user", userUrl));

        return new RatingListResponse(items, links);
    }

    /// <summary>
    /// Absolute URL of a resource of this service, as seen by the client. When
    /// the request comes from the API gateway, the proxy headers are honoured so
    /// the links point to the public address instead of the container one.
    /// </summary>
    private static string AbsoluteUrl(HttpContext context, string path)
    {
        var forwardedHost = context.Request.Headers["X-Forwarded-Host"].FirstOrDefault();
        var forwardedProto = context.Request.Headers["X-Forwarded-Proto"].FirstOrDefault();

        var host = string.IsNullOrWhiteSpace(forwardedHost) ? context.Request.Host.Value : forwardedHost;
        var scheme = string.IsNullOrWhiteSpace(forwardedProto) ? context.Request.Scheme : forwardedProto;

        return $"{scheme}://{host}{path}";
    }

    /// <summary>
    /// URL of the resource of a user in the application, exposed by the API gateway.
    /// </summary>
    private static string? UserUrl(IConfiguration configuration, string userId)
    {
        var apiBaseUrl = configuration["API_BASE_URL"];

        return string.IsNullOrWhiteSpace(apiBaseUrl)
                ? null
                : $"{apiBaseUrl.TrimEnd('/')}/api/users/{userId}";
    }
}

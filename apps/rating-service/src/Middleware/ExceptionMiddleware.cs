using Refit;
using Segundum.Dtos;

namespace Segundum.Middleware;

/// <summary>
/// Global error handler: turns the exceptions raised while a request is being
/// handled into a JSON error body with an appropriate status code.
/// </summary>
public class ExceptionMiddleware
{
    private readonly RequestDelegate _next;
    private readonly ILogger<ExceptionMiddleware> _logger;

    public ExceptionMiddleware(RequestDelegate next, ILogger<ExceptionMiddleware> logger)
    {
        _next = next;
        _logger = logger;
    }

    public async Task InvokeAsync(HttpContext context)
    {
        try
        {
            await _next(context);
        }
        catch (ApiException ex)
        {
            _logger.LogError(ex, "The sales service answered {StatusCode} for {Path}",
                    (int)ex.StatusCode, context.Request.Path);
            await WriteAsync(context, StatusCodes.Status502BadGateway,
                    "The sales service returned an unexpected response");
        }
        catch (HttpRequestException ex)
        {
            _logger.LogError(ex, "The sales service cannot be reached for {Path}", context.Request.Path);
            await WriteAsync(context, StatusCodes.Status502BadGateway,
                    "The sales service is unavailable");
        }
        catch (TaskCanceledException ex)
        {
            _logger.LogError(ex, "The sales service did not answer in time for {Path}", context.Request.Path);
            await WriteAsync(context, StatusCodes.Status504GatewayTimeout,
                    "The sales service did not answer in time");
        }
        catch (ArgumentException ex)
        {
            await WriteAsync(context, StatusCodes.Status400BadRequest, ex.Message);
        }
        catch (Exception ex)
        {
            _logger.LogError(ex, "Unexpected error while handling {Path}", context.Request.Path);
            await WriteAsync(context, StatusCodes.Status500InternalServerError,
                    "Internal server error");
        }
    }

    private static async Task WriteAsync(HttpContext context, int statusCode, string message)
    {
        if (context.Response.HasStarted)
            throw new InvalidOperationException($"Cannot write the error body: the response has already started ({message})");

        context.Response.StatusCode = statusCode;
        context.Response.ContentType = "application/json";

        await context.Response.WriteAsJsonAsync(ErrorResponse.From(statusCode, message));
    }
}

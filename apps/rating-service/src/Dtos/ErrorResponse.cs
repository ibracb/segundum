using System.Net;

namespace Segundum.Dtos;

/// <summary>
/// Body of a failed operation: the status code, its reason phrase and the
/// message the client has to show. Same shape as the error responses of the
/// other services of the platform.
/// </summary>
public record ErrorResponse(int Code, string Error, string Message)
{
    /// <summary>
    /// Builds the error of a failed operation with the status code the
    /// operation has decided for it.
    /// </summary>
    public static ErrorResponse From(int statusCode, string message) =>
        new(statusCode, ReasonPhrase(statusCode), message);

    /// <summary>
    /// Reason phrase of the status code with the wording used by the other
    /// services: the .NET enum names of the multi-word codes have no spaces.
    /// </summary>
    private static string ReasonPhrase(int statusCode) => statusCode switch
    {
        StatusCodes.Status400BadRequest => "Bad Request",
        StatusCodes.Status401Unauthorized => "Unauthorized",
        StatusCodes.Status403Forbidden => "Forbidden",
        StatusCodes.Status404NotFound => "Not Found",
        StatusCodes.Status409Conflict => "Conflict",
        StatusCodes.Status500InternalServerError => "Internal Server Error",
        StatusCodes.Status502BadGateway => "Bad Gateway",
        StatusCodes.Status504GatewayTimeout => "Gateway Timeout",
        _ => ((HttpStatusCode)statusCode).ToString(),
    };
}

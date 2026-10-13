namespace Segundum.Common;

/// <summary>
/// Outcome of a use case operation: it keeps the business message together with
/// the HTTP status code the API has to return. It is the adaptation of the
/// <c>Resultado</c> record of the course reference repository (BookleMinApi).
/// </summary>
public record Result(bool Success, int StatusCode, string? Message)
{
    public static Result Ok() => new(true, StatusCodes.Status200OK, null);
    public static Result Error(string message) => new(false, StatusCodes.Status400BadRequest, message);
    public static Result NotFound(string message) => new(false, StatusCodes.Status404NotFound, message);
    public static Result Conflict(string message) => new(false, StatusCodes.Status409Conflict, message);
}

/// <summary>
/// Outcome of a use case operation that also returns a value.
/// </summary>
public record Result<T>(bool Success, int StatusCode, string? Message, T? Value)
    : Result(Success, StatusCode, Message)
{
    public static Result<T> Ok(T value) => new(true, StatusCodes.Status200OK, null, value);
    public static new Result<T> Error(string message) =>
        new(false, StatusCodes.Status400BadRequest, message, default);
    public static new Result<T> NotFound(string message) =>
        new(false, StatusCodes.Status404NotFound, message, default);
    public static new Result<T> Conflict(string message) =>
        new(false, StatusCodes.Status409Conflict, message, default);

    /// <summary>
    /// Propagates the failure of a previous step of the operation.
    /// </summary>
    public static Result<T> From(Result other) =>
        new(other.Success, other.StatusCode, other.Message, default);
}

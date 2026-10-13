namespace Segundum.Dtos;

/// <summary>
/// Representation of a rating returned by the API.
/// </summary>
public record RatingResponse(
    string Id,
    string SaleId,
    string RaterId,
    string RatedUserId,
    string RatedUserRole,
    int Score,
    string? Comment,
    List<LinkResponse> Links);

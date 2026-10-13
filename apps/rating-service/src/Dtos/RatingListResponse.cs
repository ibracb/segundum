namespace Segundum.Dtos;

/// <summary>
/// Listing of the ratings of a user, including the links to the listed
/// resources and to the related ones.
/// </summary>
public record RatingListResponse(List<RatingResponse> Items, List<LinkResponse> Links);

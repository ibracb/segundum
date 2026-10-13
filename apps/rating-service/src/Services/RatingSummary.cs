namespace Segundum.Services;

/// <summary>
/// Summary of a rating, used to build the listings. The rated user and their
/// role are not part of it: they are the ones the listing endpoint is about,
/// so the endpoint adds them when it builds the response. It is the adaptation
/// of the <c>ActividadResumen</c> record of the course reference repository.
/// </summary>
public record RatingSummary(
    string Id,
    string SaleId,
    string RaterId,
    int Score,
    string? Comment);

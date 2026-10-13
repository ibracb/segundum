using Segundum.Models;

namespace Segundum.Events;

/// <summary>
/// Event published when a new rating has been registered. It is of interest for
/// the Usuarios microservice, which keeps a summary of the ratings of a user.
/// </summary>
public class RatingCreated : Event
{
    public required string RatingId { get; init; }
    public required string SaleId { get; init; }
    public required string RaterId { get; init; }
    public required string RatedUserId { get; init; }
    public required RatedUserRole RatedUserRole { get; init; }
    public required int Score { get; init; }

    public override string Type => "RatingCreated";
}

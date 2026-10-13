namespace Segundum.Events;

/// <summary>
/// Base class of the events published to the application bus.
/// </summary>
public abstract class Event
{
    /// <summary>
    /// The unique identifier of the event, as in the messages already published
    /// by the Java microservices (DomainEventMessage.eventId).
    /// </summary>
    public required string EventId { get; init; }

    /// <summary>
    /// The moment the event happened.
    /// </summary>
    public DateTime Timestamp { get; init; } = DateTime.UtcNow;

    /// <summary>
    /// The type of the event, used as the suffix of the routing key.
    /// </summary>
    public abstract string Type { get; }
}

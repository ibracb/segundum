using Segundum.Events;

namespace Segundum.Ports;

/// <summary>
/// Outbound port for publishing domain events to the application bus.
/// </summary>
public interface IEventPublisher
{
    Task PublishAsync(Event @event);
}

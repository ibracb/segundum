using Segundum.Events;
using Segundum.Ports;

namespace Segundum.Fakes;

public class FakeEventPublisher : IEventPublisher
{
    private readonly List<Event> _published = new();

    public bool ThrowOnPublish { get; set; }

    public IReadOnlyList<Event> Published => _published;

    public Task PublishAsync(Event @event)
    {
        if (ThrowOnPublish)
            throw new InvalidOperationException("The event bus is not available.");

        _published.Add(@event);
        return Task.CompletedTask;
    }
}

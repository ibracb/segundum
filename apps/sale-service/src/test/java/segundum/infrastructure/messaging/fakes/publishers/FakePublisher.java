package segundum.infrastructure.messaging.fakes.publishers;

import java.util.ArrayList;
import java.util.List;

import segundum.domain.events.DomainEvent;
import segundum.application.outbound.DomainEventPublisher;

public class FakePublisher implements DomainEventPublisher {

	private final List<DomainEvent> published = new ArrayList<>();

	@Override
	public <T extends DomainEvent> void publish(T event) {
		published.add(event);
	}

	public List<DomainEvent> getPublishedEvents() {
		return published;
	}

	public void clear() {
		published.clear();
	}
}

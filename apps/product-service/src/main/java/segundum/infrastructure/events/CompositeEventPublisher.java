package segundum.infrastructure.events;

import java.util.List;

import org.springframework.stereotype.Component;

import segundum.domain.events.DomainEvent;
import segundum.application.outbound.DomainEventPublisher;

/**
 * Represents an event publisher that delegates to a list of event publishers.
 */
@Component
public class CompositeEventPublisher implements DomainEventPublisher {

	/**
	 * The list of publishers to delegate to.
	 */
	private final List<EventPublisher> publishers;

	/**
	 * Constructs a new CompositeEventPublisher with the given publishers.
	 *
	 * @param publishers the list of event publishers
	 */
	public CompositeEventPublisher(List<EventPublisher> publishers) {
		this.publishers = publishers;
	}

	@Override
	public <T extends DomainEvent> void publish(T event) {
		for (EventPublisher publisher : publishers) {
			publisher.publish(event);
		}
	}

}

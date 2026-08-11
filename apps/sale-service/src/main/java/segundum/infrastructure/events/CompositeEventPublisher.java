package segundum.infrastructure.events;

import java.util.List;

import org.springframework.stereotype.Component;

import segundum.domain.events.DomainEvent;
import segundum.domain.outbound.DomainEventPublisher;

/**
 * Represents a domain event publisher that forwards events to multiple publishers.
 */
@Component
public class CompositeEventPublisher implements DomainEventPublisher {

	/**
	 * The list of event publishers to forward events to.
	 */
	private final List<EventPublisher> publishers;

	/**
	 * Constructs a new CompositeEventPublisher with the given publishers.
	 *
	 * @param publishers the list of event publishers to forward events to
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

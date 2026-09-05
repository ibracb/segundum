package segundum.application.outbound;

import segundum.domain.events.DomainEvent;

/**
 * Interface for publishing domain events.
 */
public interface DomainEventPublisher {
	
	/**
	 * Publishes a domain event.
	 *
	 * @param event the domain event to publish
	 * @param <T>   the type of the domain event
	 */
	<T extends DomainEvent> void publish(T event);

}

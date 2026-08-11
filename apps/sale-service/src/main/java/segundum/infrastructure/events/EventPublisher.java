package segundum.infrastructure.events;

import segundum.domain.events.DomainEvent;

/**
 * Represents a publisher for domain events.
 */
public interface EventPublisher {

    /**
     * Publishes the given domain event.
     *
     * @param event the domain event to publish
     */
    <T extends DomainEvent> void publish(T event);

}

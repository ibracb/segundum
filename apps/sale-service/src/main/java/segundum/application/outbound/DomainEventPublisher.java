package segundum.application.outbound;

import segundum.domain.events.DomainEvent;

/**
 * Represents the publisher for domain events.
 */
public interface DomainEventPublisher {

    /**
     * Publishes the given domain event.
     *
     * @param event the domain event to publish
     */
    <T extends DomainEvent> void publish(T event);

}

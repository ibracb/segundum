package segundum.infrastructure.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import segundum.domain.events.DomainEvent;

@Component
@Order(1)
/**
 * Represents an event publisher that publishes events through Spring.
 */
public class SpringEventPublisher implements EventPublisher {

	/**
	 * The Spring application event publisher.
	 */
	private final ApplicationEventPublisher publisher;

	/**
	 * Constructs a new SpringEventPublisher with the given Spring publisher.
	 *
	 * @param publisher the Spring application event publisher
	 */
	public SpringEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public <T extends DomainEvent> void publish(T event) {
		publisher.publishEvent(event);
	}

}

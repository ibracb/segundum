package segundum.infrastructure.events;

import org.springframework.context.ApplicationEventPublisher;

import segundum.domain.events.DomainEvent;
import segundum.domain.outbound.DomainEventPublisher;

public class SpringEventPublisher implements DomainEventPublisher {

	private final ApplicationEventPublisher publisher;

	public SpringEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public <T extends DomainEvent> void publish(T event) {
		publisher.publishEvent(event);
	}

}

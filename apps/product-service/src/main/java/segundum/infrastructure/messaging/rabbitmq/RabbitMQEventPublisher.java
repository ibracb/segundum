package segundum.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import segundum.domain.events.CategoryCreated;
import segundum.domain.events.DomainEvent;
import segundum.infrastructure.events.EventPublisher;
import segundum.infrastructure.messaging.messages.ProductMessageMapper;

/**
 * Represents an event publisher that publishes events through RabbitMQ.
 */
@Component
@Order(2)
public class RabbitMQEventPublisher implements EventPublisher {

	/**
	 * The RabbitMQ template used to send messages.
	 */
	private final RabbitTemplate rabbitTemplate;

	/**
	 * The mapper used to map domain events to messages.
	 */
	private final ProductMessageMapper mapper;

	/**
	 * Constructs a new RabbitMQEventPublisher with the given dependencies.
	 *
	 * @param rabbitTemplate the RabbitMQ template
	 * @param mapper the product message mapper
	 */
	public RabbitMQEventPublisher(RabbitTemplate rabbitTemplate, ProductMessageMapper mapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.mapper = mapper;
	}

	@Override
	public <T extends DomainEvent> void publish(T event) {
		if (event instanceof CategoryCreated) {
			return;
		}
		rabbitTemplate.convertAndSend(
		          RabbitMQConfig.EXCHANGE_NAME,
		          RabbitMQConfig.ROUTING_KEY + event.getType(),
		          mapper.map(event));
	}

}

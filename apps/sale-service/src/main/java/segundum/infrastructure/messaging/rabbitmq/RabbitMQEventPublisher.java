package segundum.infrastructure.messaging.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import segundum.domain.events.DomainEvent;
import segundum.infrastructure.events.EventPublisher;
import segundum.infrastructure.messaging.messages.SaleMessageMapper;

/**
 * Represents an event publisher that publishes domain events to RabbitMQ.
 */
@Component
@Order(2)
public class RabbitMQEventPublisher implements EventPublisher {
	
	/**
	 * The RabbitTemplate for sending messages.
	 */
	private final RabbitTemplate rabbitTemplate;
	
	/**
	 * The mapper for converting domain events into messages.
	 */
	private final SaleMessageMapper mapper;
	
	/**
	 * Constructs a new RabbitMQEventPublisher with the given dependencies.
	 *
	 * @param rabbitTemplate the RabbitTemplate for sending messages
	 * @param mapper         the mapper for converting domain events into messages
	 */
	public RabbitMQEventPublisher(RabbitTemplate rabbitTemplate, SaleMessageMapper mapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.mapper = mapper;
	}
	
	@Override
	public <T extends DomainEvent> void publish(T event) {
		rabbitTemplate.convertAndSend(
		          RabbitMQConfig.EXCHANGE_NAME, 
		          RabbitMQConfig.ROUTING_KEY + event.getType(), 
		          mapper.map(event));
	}

}

package segundum.infrastructure.messaging.rabbitmq.consumers;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import segundum.application.eventhandlers.SaleEventHandler;
import segundum.application.eventhandlers.UserEventHandler;
import segundum.application.events.sales.SaleCancelled;
import segundum.application.events.sales.SaleCompleted;
import segundum.application.events.sales.SaleReserved;
import segundum.application.events.users.UserDeactivated;
import segundum.application.events.users.UserRegistered;
import segundum.application.events.users.UserUpdated;
import segundum.infrastructure.messaging.rabbitmq.config.RabbitMQConfig;

@Component
/**
 * Represents a consumer of RabbitMQ domain events.
 */
public class RabbitMQEventConsumer {
	
	/**
	 * The handler for user events.
	 */
	private final UserEventHandler userEventHandler;
	
	/**
	 * The handler for sale events.
	 */
	private final SaleEventHandler saleEventHandler;
	
	/**
	 * Constructs a new RabbitMQEventConsumer with the given handlers.
	 *
	 * @param userEventHandler the user event handler
	 * @param saleEventHandler the sale event handler
	 */
	public RabbitMQEventConsumer(UserEventHandler userEventHandler,
			SaleEventHandler saleEventHandler) {
		this.userEventHandler = userEventHandler;
		this.saleEventHandler = saleEventHandler;
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void consume(Map<String, Object> message) {
		String type = (String) message.get("type");
		if(type.equals("UserRegistered")) {
			UUID userId = UUID.fromString((String) message.get("userId"));
			String name = (String) message.get("name");
			String surname = (String) message.get("surname");
			String email = (String) message.get("email");
			UserRegistered event = new UserRegistered(userId, name, surname, email);
			userEventHandler.onUserRegistered(event);
		}
		else if(type.equals("UserUpdated")) {
			UUID userId = UUID.fromString((String) message.get("userId"));
			String name = (String) message.get("name");
			String surname = (String) message.get("surname");
			UserUpdated event = new UserUpdated(userId, name, surname);
			userEventHandler.onUserUpdated(event);
		}
		else if(type.equals("UserDeactivated")) {
			UUID userId = UUID.fromString((String) message.get("userId"));
			UserDeactivated event = new UserDeactivated(userId);
			userEventHandler.onUserDeactivated(event);
		}
		else if(type.equals("SaleReserved")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleReserved event = new SaleReserved(productId);
			saleEventHandler.onSaleReserved(event);
		}
		else if(type.equals("SaleCompleted")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCompleted event = new SaleCompleted(productId);
			saleEventHandler.onSaleCompleted(event);
		}
		else if(type.equals("SaleCancelledByPurchaser")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCancelled event = new SaleCancelled(productId);
			saleEventHandler.onSaleCancelled(event);
		}
		else if(type.equals("SaleCancelledBySeller")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCancelled event = new SaleCancelled(productId);
			saleEventHandler.onSaleCancelled(event);
		}
	}

}

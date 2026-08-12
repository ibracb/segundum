package segundum.infrastructure.messaging.rabbitmq.consumers;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import segundum.application.events.sales.SaleCancelled;
import segundum.application.events.sales.SaleCompleted;
import segundum.application.events.sales.SaleReserved;
import segundum.application.events.users.UserDeactivated;
import segundum.application.events.users.UserRegistered;
import segundum.application.events.users.UserUpdated;
import segundum.infrastructure.facades.SaleEventFacade;
import segundum.infrastructure.facades.UserEventFacade;
import segundum.infrastructure.messaging.rabbitmq.config.RabbitMQConfig;

@Component
/**
 * Represents a consumer of RabbitMQ domain events.
 */
public class RabbitMQEventConsumer {
	
	/**
	 * The facade for the users bounded context event handlers.
	 */
	private final UserEventFacade userEventFacade;

	/**
	 * The facade for the sales bounded context event handlers.
	 */
	private final SaleEventFacade saleEventFacade;

	/**
	 * Constructs a new RabbitMQEventConsumer with the given facades.
	 *
	 * @param userEventFacade the facade for the users bounded context event handlers
	 * @param saleEventFacade the facade for the sales bounded context event handlers
	 */
	public RabbitMQEventConsumer(UserEventFacade userEventFacade, SaleEventFacade saleEventFacade) {
		this.userEventFacade = userEventFacade;
		this.saleEventFacade = saleEventFacade;
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
			userEventFacade.onUserRegistered(event);
		}
		else if(type.equals("UserUpdated")) {
			UUID userId = UUID.fromString((String) message.get("userId"));
			String name = (String) message.get("name");
			String surname = (String) message.get("surname");
			UserUpdated event = new UserUpdated(userId, name, surname);
			userEventFacade.onUserUpdated(event);
		}
		else if(type.equals("UserDeactivated")) {
			UUID userId = UUID.fromString((String) message.get("userId"));
			UserDeactivated event = new UserDeactivated(userId);
			userEventFacade.onUserDeactivated(event);
		}
		else if(type.equals("SaleReserved")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleReserved event = new SaleReserved(productId);
			saleEventFacade.onSaleReserved(event);
		}
		else if(type.equals("SaleCompleted")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCompleted event = new SaleCompleted(productId);
			saleEventFacade.onSaleCompleted(event);
		}
		else if(type.equals("SaleCancelledByPurchaser")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCancelled event = new SaleCancelled(productId);
			saleEventFacade.onSaleCancelled(event);
		}
		else if(type.equals("SaleCancelledBySeller")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCancelled event = new SaleCancelled(productId);
			saleEventFacade.onSaleCancelled(event);
		}
	}

}

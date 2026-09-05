package segundum.infrastructure.messaging.rabbitmq;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import segundum.application.notifications.sales.SaleCancelledNotification;
import segundum.application.notifications.sales.SaleCompletedNotification;
import segundum.application.notifications.sales.SaleReservedNotification;
import segundum.application.notifications.users.UserDeactivatedNotification;
import segundum.application.notifications.users.UserRegisteredNotification;
import segundum.application.notifications.users.UserUpdatedNotification;
import segundum.infrastructure.facades.SaleNotificationFacade;
import segundum.infrastructure.facades.UserNotificationFacade;

/**
 * Represents a consumer of RabbitMQ domain events.
 */
@Component
public class RabbitMQConsumer {
	
	/**
	 * The facade for the users bounded context event handlers.
	 */
	private final UserNotificationFacade userNotificationFacade;

	/**
	 * The facade for the sales bounded context event handlers.
	 */
	private final SaleNotificationFacade saleNotificationFacade;

	/**
	 * Constructs a new RabbitMQConsumer with the given facades.
	 *
	 * @param userNotificationFacade the facade for the users bounded context event handlers
	 * @param saleNotificationFacade the facade for the sales bounded context event handlers
	 */
	public RabbitMQConsumer(UserNotificationFacade userNotificationFacade, SaleNotificationFacade saleNotificationFacade) {
		this.userNotificationFacade = userNotificationFacade;
		this.saleNotificationFacade = saleNotificationFacade;
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void consume(Map<String, Object> message) {
		String type = (String) message.get("type");
		if(type.equals("UserRegistered")) {
			UUID userId = UUID.fromString((String) message.get("userId"));
			String name = (String) message.get("name");
			String surname = (String) message.get("surname");
			String email = (String) message.get("email");
			UserRegisteredNotification event = new UserRegisteredNotification(userId, name, surname, email);
			userNotificationFacade.onUserRegisteredNotification(event);
		}
		else if(type.equals("UserUpdated")) {
			UUID userId = UUID.fromString((String) message.get("userId"));
			String name = (String) message.get("name");
			String surname = (String) message.get("surname");
			UserUpdatedNotification event = new UserUpdatedNotification(userId, name, surname);
			userNotificationFacade.onUserUpdatedNotification(event);
		}
		else if(type.equals("UserDeactivated")) {
			UUID userId = UUID.fromString((String) message.get("userId"));
			UserDeactivatedNotification event = new UserDeactivatedNotification(userId);
			userNotificationFacade.onUserDeactivatedNotification(event);
		}
		else if(type.equals("SaleReserved")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleReservedNotification event = new SaleReservedNotification(productId);
			saleNotificationFacade.onSaleReservedNotification(event);
		}
		else if(type.equals("SaleCompleted")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCompletedNotification event = new SaleCompletedNotification(productId);
			saleNotificationFacade.onSaleCompletedNotification(event);
		}
		else if(type.equals("SaleCancelledByPurchaser")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCancelledNotification event = new SaleCancelledNotification(productId);
			saleNotificationFacade.onSaleCancelledNotification(event);
		}
		else if(type.equals("SaleCancelledBySeller")) {
			UUID productId = UUID.fromString((String) message.get("productId"));
			SaleCancelledNotification event = new SaleCancelledNotification(productId);
			saleNotificationFacade.onSaleCancelledNotification(event);
		}
	}

}

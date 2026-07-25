package segundum.infrastructure.messaging.rabbitmq.consumers;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import segundum.application.eventhandlers.users.UserDeactivatedHandler;
import segundum.application.eventhandlers.users.UserRegisteredHandler;
import segundum.application.eventhandlers.users.UserUpdatedHandler;
import segundum.application.events.users.UserDeactivated;
import segundum.application.events.users.UserRegistered;
import segundum.application.events.users.UserUpdated;
import segundum.infrastructure.messaging.rabbitmq.config.RabbitMQConfig;
import segundum.infrastructure.messaging.rabbitmq.messages.UserDeactivatedMessage;
import segundum.infrastructure.messaging.rabbitmq.messages.UserRegisteredMessage;
import segundum.infrastructure.messaging.rabbitmq.messages.UserUpdatedMessage;

/**
 * RabbitMQ consumer for user events.
 */
@Component
@RabbitListener(queues = RabbitMQConfig.USERS_QUEUE)
public class RabbitMQUserEventConsumer {

	/**
	 * The handler for user registered events.
	 */
	private final UserRegisteredHandler userRegisteredHandler;
	/**
	 * The handler for user updated events.
	 */
	private final UserUpdatedHandler userUpdatedHandler;
	/**
	 * The handler for user deactivated events.
	 */
	private final UserDeactivatedHandler userDeactivatedHandler;

	/**
	 * Constructs a new RabbitMQUserEventConsumer with the given handlers.
	 *
	 * @param userRegisteredHandler the handler for user registered events
	 * @param userUpdatedHandler the handler for user updated events
	 * @param userDeactivatedHandler the handler for user deactivated events
	 */
	public RabbitMQUserEventConsumer(UserRegisteredHandler userRegisteredHandler,
			UserUpdatedHandler userUpdatedHandler,
			UserDeactivatedHandler userDeactivatedHandler) {
		this.userRegisteredHandler = userRegisteredHandler;
		this.userUpdatedHandler = userUpdatedHandler;
		this.userDeactivatedHandler = userDeactivatedHandler;
	}

	/**
	 * Consumes user registered events from the queue.
	 *
	 * @param message the deserialized user registered event
	 */
	@RabbitHandler
	public void consumeUserRegistered(UserRegisteredMessage message) {
		userRegisteredHandler.handle(new UserRegistered(
				UUID.fromString(message.getUserId().getValue()),
				message.getName().getValue(),
				message.getSurname().getValue(),
				message.getEmail().getValue()));
	}

	/**
	 * Consumes user updated events from the queue.
	 *
	 * @param message the deserialized user updated event
	 */
	@RabbitHandler
	public void consumeUserUpdated(UserUpdatedMessage message) {
		userUpdatedHandler.handle(new UserUpdated(
				UUID.fromString(message.getUserId().getValue()),
				message.getName().getValue(),
				message.getSurname().getValue()));
	}

	/**
	 * Consumes user deactivated events from the queue.
	 *
	 * @param message the deserialized user deactivated event
	 */
	@RabbitHandler
	public void consumeUserDeactivated(UserDeactivatedMessage message) {
		userDeactivatedHandler.handle(new UserDeactivated(
				UUID.fromString(message.getUserId().getValue())));
	}

}

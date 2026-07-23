package segundum.infrastructure.messaging.rabbitmq.consumers;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import segundum.application.eventhandlers.users.UserDeletedHandler;
import segundum.application.eventhandlers.users.UserRegisteredHandler;
import segundum.application.eventhandlers.users.UserUpdatedHandler;
import segundum.application.events.users.UserDeleted;
import segundum.application.events.users.UserRegistered;
import segundum.application.events.users.UserUpdated;
import segundum.infrastructure.messaging.rabbitmq.config.RabbitMQConfig;
import segundum.infrastructure.messaging.rabbitmq.messages.UserDeletedMessage;
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
	 * The handler for user deleted events.
	 */
	private final UserDeletedHandler userDeletedHandler;

	/**
	 * Constructs a new RabbitMQUserEventConsumer with the given handlers.
	 *
	 * @param userRegisteredHandler the handler for user registered events
	 * @param userUpdatedHandler the handler for user updated events
	 * @param userDeletedHandler the handler for user deleted events
	 */
	public RabbitMQUserEventConsumer(UserRegisteredHandler userRegisteredHandler,
			UserUpdatedHandler userUpdatedHandler,
			UserDeletedHandler userDeletedHandler) {
		this.userRegisteredHandler = userRegisteredHandler;
		this.userUpdatedHandler = userUpdatedHandler;
		this.userDeletedHandler = userDeletedHandler;
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
	 * Consumes user deleted events from the queue.
	 *
	 * @param message the deserialized user deleted event
	 */
	@RabbitHandler
	public void consumeUserDeleted(UserDeletedMessage message) {
		userDeletedHandler.handle(new UserDeleted(
				UUID.fromString(message.getUserId().getValue())));
	}

}

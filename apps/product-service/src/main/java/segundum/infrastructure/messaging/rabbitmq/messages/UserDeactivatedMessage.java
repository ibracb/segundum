package segundum.infrastructure.messaging.rabbitmq.messages;

/**
 * Message DTO for user deactivated messages.
 */
public class UserDeactivatedMessage extends UserMessage {

	/**
	 * The user identifier.
	 */
	private ValueContainer userId;

	/**
	 * Returns the user identifier.
	 *
	 * @return the user identifier
	 */
	public ValueContainer getUserId() {
		return userId;
	}

}

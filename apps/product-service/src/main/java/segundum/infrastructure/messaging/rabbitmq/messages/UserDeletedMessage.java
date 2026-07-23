package segundum.infrastructure.messaging.rabbitmq.messages;

/**
 * Message DTO for user deleted messages.
 */
public class UserDeletedMessage extends UserMessage {

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

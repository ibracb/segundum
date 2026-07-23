package segundum.infrastructure.messaging.rabbitmq.messages;

/**
 * Message DTO for user updated messages.
 */
public class UserUpdatedMessage extends UserMessage {

	/**
	 * The user identifier.
	 */
	private ValueContainer userId;

	/**
	 * The user name.
	 */
	private ValueContainer name;

	/**
	 * The user surname.
	 */
	private ValueContainer surname;

	/**
	 * Returns the user identifier.
	 *
	 * @return the user identifier
	 */
	public ValueContainer getUserId() {
		return userId;
	}

	/**
	 * Returns the user name.
	 *
	 * @return the user name
	 */
	public ValueContainer getName() {
		return name;
	}

	/**
	 * Returns the user surname.
	 *
	 * @return the user surname
	 */
	public ValueContainer getSurname() {
		return surname;
	}

}

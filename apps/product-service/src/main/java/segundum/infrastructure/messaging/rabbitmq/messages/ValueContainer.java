package segundum.infrastructure.messaging.rabbitmq.messages;

/**
 * Wrapper class for JSON values in RabbitMQ messages.
 */
public class ValueContainer {

	/**
	 * The value.
	 */
	private String value;

	/**
	 * Returns the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}

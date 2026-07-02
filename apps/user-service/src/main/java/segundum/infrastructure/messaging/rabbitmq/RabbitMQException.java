package segundum.infrastructure.messaging.rabbitmq;

/**
 * Exception thrown when an error occurs while interacting with RabbitMQ.
 */
@SuppressWarnings("serial")
public class RabbitMQException extends RuntimeException {
	
	/**
	 * Constructs a new RabbitMQException with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public RabbitMQException(String message) {
		super(message);
	}

}

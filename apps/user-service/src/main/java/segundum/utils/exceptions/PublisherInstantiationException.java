package segundum.utils.exceptions;

/**
 * Exception thrown when there is an error instantiating a publisher.
 */
@SuppressWarnings("serial")
public class PublisherInstantiationException extends RuntimeException {

	/**
	 * Constructs a new PublisherInstantiationException with the specified detail message and cause.
	 * @param message The detail message
	 * @param cause The cause of the exception
	 */
    public PublisherInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

package segundum.utils.exceptions;

/**
 * Exception thrown when there is an error instantiating a use case.
 */
@SuppressWarnings("serial")
public class UseCaseInstantiationException extends RuntimeException {

	/**
	 * Constructs a new UseCaseInstantiationException with the specified detail message and cause.
	 * @param message	The detail message
	 * @param cause	The cause of the exception
	 */
    public UseCaseInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}

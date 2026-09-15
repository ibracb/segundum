package segundum.infrastructure.client;

/**
 * Exception thrown when an external service call fails.
 */
@SuppressWarnings("serial")
public class ExternalServiceException extends RuntimeException {

    /**
     * Constructs a new ExternalServiceException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public ExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}

package segundum.infrastructure.client;

import java.io.IOException;

/**
 * Exception thrown when an external service call fails.
 */
@SuppressWarnings("serial")
public class ExternalServiceException extends RuntimeException {

    /**
     * Constructs a new ExternalServiceException with the given message and cause.
     *
     * @param message the detail message
     * @param cause   the underlying cause
     */
    public ExternalServiceException(String message, IOException cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ExternalServiceException with the given message.
     *
     * @param message the detail message
     */
    public ExternalServiceException(String message) {
        super(message);
    }

}

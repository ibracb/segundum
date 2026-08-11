package segundum.domain.exceptions;

/**
 * Exception thrown when a domain rule is violated in the system.
 */
@SuppressWarnings("serial")
public abstract class DomainException extends RuntimeException {

	/**
	 * Constructs a new DomainException with the given message.
	 *
	 * @param message the exception message
	 */
    protected DomainException(String message) {
        super(message);
    }

}

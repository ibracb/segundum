package segundum.domain.exceptions;

/**
 * Base class for all domain exceptions.
 */
@SuppressWarnings("serial")
public abstract class DomainException extends RuntimeException {
	
	/**
	 * Constructs a new DomainException with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	protected DomainException(String message) {
		super(message);
	}

}

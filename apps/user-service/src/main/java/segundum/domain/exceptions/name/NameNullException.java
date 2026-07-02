package segundum.domain.exceptions.name;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a name is null.
 */
@SuppressWarnings("serial")
public class NameNullException extends DomainException {
	
	/**
	 * Constructs a new NameNullException with a message indicating that the name cannot be null.
	 */
	public NameNullException() {
		super("Name cannot be null");
	}

}

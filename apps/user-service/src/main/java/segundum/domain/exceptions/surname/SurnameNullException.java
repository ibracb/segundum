package segundum.domain.exceptions.surname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a surname is null.
 */
@SuppressWarnings("serial")
public class SurnameNullException extends DomainException {

	/**
	 * Constructs a new SurnameNullException with a message indicating that the surname cannot be null.
	 */
	public SurnameNullException() {
		super("Surname cannot be null");
	}

}

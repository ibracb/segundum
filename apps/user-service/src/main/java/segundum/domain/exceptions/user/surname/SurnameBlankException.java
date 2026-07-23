package segundum.domain.exceptions.user.surname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a surname is blank or empty.
 */
@SuppressWarnings("serial")
public class SurnameBlankException extends DomainException {

	/**
	 * Constructs a new SurnameBlankException with a default message.
	 */
	public SurnameBlankException() {
		super("Surname cannot be blank");
	}

}

package segundum.domain.exceptions.user.surname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a surname is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class SurnameInvalidFormatException extends DomainException {

	/**
	 * Constructs a new SurnameInvalidFormatException with a default message indicating that the surname can only
	 * contain letters and spaces.
	 */
	public SurnameInvalidFormatException() {
		super("Surname must contain only letters and spaces");
	}

}

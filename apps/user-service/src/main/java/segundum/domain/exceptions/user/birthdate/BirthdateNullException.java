package segundum.domain.exceptions.user.birthdate;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a birthdate is null.
 */
@SuppressWarnings("serial")
public class BirthdateNullException extends DomainException {

	/**
	 * Constructs a new BirthdateNullException with a message indicating that the birthdate cannot be null.
	 */
	public BirthdateNullException() {
		super("Birthdate cannot be null");
	}

}

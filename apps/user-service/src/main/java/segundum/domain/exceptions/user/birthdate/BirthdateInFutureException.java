package segundum.domain.exceptions.user.birthdate;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a birthdate is in the future.
 */
@SuppressWarnings("serial")
public class BirthdateInFutureException extends DomainException {

	/**
	 * Constructs a new BirthdateInFutureException with a message indicating that the birthdate cannot be in the
	 * future.
	 */
	public BirthdateInFutureException() {
		super("Birthdate cannot be in the future");
	}

}

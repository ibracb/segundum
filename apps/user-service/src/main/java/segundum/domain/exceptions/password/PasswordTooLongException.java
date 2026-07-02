package segundum.domain.exceptions.password;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a password exceeds the maximum length requirement.
 */
@SuppressWarnings("serial")
public class PasswordTooLongException extends DomainException {

	/**
	 * Constructs a new PasswordTooLongException with a default message.
	 */
	public PasswordTooLongException() {
		super("Password cannot be longer than 64 characters");
	}

}

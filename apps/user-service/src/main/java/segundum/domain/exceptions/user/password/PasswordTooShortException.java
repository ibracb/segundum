package segundum.domain.exceptions.user.password;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a password does not meet the minimum length requirement.
 */
@SuppressWarnings("serial")
public class PasswordTooShortException extends DomainException {

	/**
	 * Constructs a new PasswordTooShortException with a default message.
	 */
	public PasswordTooShortException() {
		super("Password must be at least 8 characters long");
	}

}

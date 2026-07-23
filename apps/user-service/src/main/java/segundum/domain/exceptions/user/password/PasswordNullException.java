package segundum.domain.exceptions.user.password;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a password is null.
 */
@SuppressWarnings("serial")
public class PasswordNullException extends DomainException {

	/**
	 * Constructs a new PasswordNullException with a message indicating that the password cannot be null.
	 */
	public PasswordNullException() {
		super("Password cannot be null");
	}

}

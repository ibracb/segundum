package segundum.domain.exceptions.user.password;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a password is blank or empty.
 */
@SuppressWarnings("serial")
public class PasswordBlankException extends DomainException {

	/**
	 * Constructs a new PasswordBlankException with a default message.
	 */
	public PasswordBlankException() {
		super("Password cannot be blank");
	}

}

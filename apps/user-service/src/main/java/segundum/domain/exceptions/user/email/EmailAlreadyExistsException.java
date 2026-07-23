package segundum.domain.exceptions.user.email;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when an email address is already in use.
 */
@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends DomainException {

	/**
	 * Constructs a new EmailAlreadyExistsException with a message indicating that the specified email address is already
	 * in use.
	 * 
	 * @param email the email address that is already in use
	 */
	public EmailAlreadyExistsException(String email) {
		super("The email " + email + " is already in use.");
	}

}

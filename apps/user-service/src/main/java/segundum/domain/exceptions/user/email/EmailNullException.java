package segundum.domain.exceptions.user.email;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when an email is null.
 */
@SuppressWarnings("serial")
public class EmailNullException extends DomainException {

	/**
	 * Constructs a new EmailNullException with a message indicating that the email cannot be null.
	 */
	public EmailNullException() {
		super("Email cannot be null");
	}

}

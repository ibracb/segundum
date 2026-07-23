package segundum.domain.exceptions.user.email;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when an email is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class EmailInvalidFormatException extends DomainException {

	/**
	 * Constructs a new EmailInvalidFormatException with a default message.
	 */
	public EmailInvalidFormatException() {
		super("Email is not valid");
	}

}

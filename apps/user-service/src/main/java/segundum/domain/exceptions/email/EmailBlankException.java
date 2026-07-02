package segundum.domain.exceptions.email;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when an email is blank or empty.
 */
@SuppressWarnings("serial")
public class EmailBlankException extends DomainException {

	/**
	 * Constructs a new EmailBlankException with a default message.
	 */
	public EmailBlankException() {
		super("Email cannot be blank");
	}

}

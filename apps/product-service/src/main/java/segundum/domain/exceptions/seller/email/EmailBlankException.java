package segundum.domain.exceptions.seller.email;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller email is blank or empty.
 */
@SuppressWarnings("serial")
public class EmailBlankException extends DomainException {

	/**
	 * Constructs a new EmailBlankException with a default message.
	 */
	public EmailBlankException() {
		super("Seller email cannot be blank");
	}

}

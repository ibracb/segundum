package segundum.domain.exceptions.seller.email;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller email is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class EmailInvalidFormatException extends DomainException {

	/**
	 * Constructs a new EmailInvalidFormatException with a default message.
	 */
	public EmailInvalidFormatException() {
		super("Seller email is not valid");
	}

}

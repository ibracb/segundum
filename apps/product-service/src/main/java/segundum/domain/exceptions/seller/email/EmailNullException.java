package segundum.domain.exceptions.seller.email;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller email is null.
 */
@SuppressWarnings("serial")
public class EmailNullException extends DomainException {

	/**
	 * Constructs a new EmailNullException with a message indicating that the seller email cannot be null.
	 */
	public EmailNullException() {
		super("Seller email cannot be null");
	}

}

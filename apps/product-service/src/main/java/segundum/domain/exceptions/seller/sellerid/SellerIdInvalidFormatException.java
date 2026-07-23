package segundum.domain.exceptions.seller.sellerid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller ID is invalid due to incorrect format.
 */
@SuppressWarnings("serial")
public class SellerIdInvalidFormatException extends DomainException {

	/**
	 * Constructs a new SellerIdInvalidFormatException with a default message.
	 */
	public SellerIdInvalidFormatException() {
		super("Seller ID is not a valid UUID");
	}

}

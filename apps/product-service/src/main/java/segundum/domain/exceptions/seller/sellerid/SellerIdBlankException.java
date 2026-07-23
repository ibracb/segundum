package segundum.domain.exceptions.seller.sellerid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller ID is blank or empty.
 */
@SuppressWarnings("serial")
public class SellerIdBlankException extends DomainException {

	/**
	 * Constructs a new SellerIdBlankException with a default message.
	 */
	public SellerIdBlankException() {
		super("Seller ID cannot be blank");
	}

}

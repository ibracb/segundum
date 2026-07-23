package segundum.domain.exceptions.seller.sellerid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a seller ID is null.
 */
@SuppressWarnings("serial")
public class SellerIdNullException extends DomainException {

	/**
	 * Constructs a new SellerIdNullException with a message indicating that the seller ID cannot be null.
	 */
	public SellerIdNullException() {
		super("Seller ID cannot be null");
	}

}

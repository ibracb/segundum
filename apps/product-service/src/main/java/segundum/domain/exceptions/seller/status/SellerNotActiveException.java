package segundum.domain.exceptions.seller.status;

import segundum.domain.exceptions.DomainException;
import segundum.domain.models.seller.SellerId;

/**
 * Exception thrown when a seller is not active.
 */
@SuppressWarnings("serial")
public class SellerNotActiveException extends DomainException {

	/**
	 * Constructs a new SellerNotActiveException for the given seller.
	 *
	 * @param sellerId the identifier of the seller
	 */
	public SellerNotActiveException(SellerId sellerId) {
		super("Seller with ID " + sellerId.getValue() + " is not active.");
	}

}

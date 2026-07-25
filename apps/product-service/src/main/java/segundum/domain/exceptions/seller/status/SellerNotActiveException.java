package segundum.domain.exceptions.seller.status;

import segundum.domain.exceptions.DomainException;
import segundum.domain.models.seller.SellerId;

@SuppressWarnings("serial")
public class SellerNotActiveException extends DomainException {

	public SellerNotActiveException(SellerId sellerId) {
		super("Seller with ID " + sellerId.getValue() + " is not active.");
	}

}

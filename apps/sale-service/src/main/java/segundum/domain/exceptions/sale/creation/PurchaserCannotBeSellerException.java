package segundum.domain.exceptions.sale.creation;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the purchaser cannot be the seller.
 */
@SuppressWarnings("serial")
public class PurchaserCannotBeSellerException extends DomainException {

/**
 * Constructs a new PurchaserCannotBeSellerException.
 */
public PurchaserCannotBeSellerException() {
        super("Purchaser cannot be the seller");
    }

}

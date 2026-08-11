package segundum.domain.exceptions.sale.status;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the sale is not owned by the seller.
 */
@SuppressWarnings("serial")
public class SaleNotOwnedBySellerException extends DomainException {

/**
 * Constructs a new SaleNotOwnedBySellerException.
 */
public SaleNotOwnedBySellerException() {
        super("Sale is not owned by this seller");
    }

}

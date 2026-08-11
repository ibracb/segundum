package segundum.domain.exceptions.sale.sellerid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller id is null.
 */
@SuppressWarnings("serial")
public class SellerIdNullException extends DomainException {

/**
 * Constructs a new SellerIdNullException.
 */
public SellerIdNullException() {
        super("Seller ID cannot be null");
    }

}

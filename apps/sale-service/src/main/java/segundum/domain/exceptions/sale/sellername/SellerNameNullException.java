package segundum.domain.exceptions.sale.sellername;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller name is null.
 */
@SuppressWarnings("serial")
public class SellerNameNullException extends DomainException {

/**
 * Constructs a new SellerNameNullException.
 */
public SellerNameNullException() {
        super("Seller name is null");
    }

}

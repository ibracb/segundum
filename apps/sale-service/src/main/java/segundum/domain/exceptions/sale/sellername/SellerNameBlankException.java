package segundum.domain.exceptions.sale.sellername;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller name is blank.
 */
@SuppressWarnings("serial")
public class SellerNameBlankException extends DomainException {

/**
 * Constructs a new SellerNameBlankException.
 */
public SellerNameBlankException() {
        super("Seller name is blank");
    }

}

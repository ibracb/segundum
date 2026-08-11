package segundum.domain.exceptions.sale.sellerid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller id is blank.
 */
@SuppressWarnings("serial")
public class SellerIdBlankException extends DomainException {

/**
 * Constructs a new SellerIdBlankException.
 */
public SellerIdBlankException() {
        super("Seller ID cannot be blank");
    }

}

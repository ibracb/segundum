package segundum.domain.exceptions.sale.sellerid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller id has an invalid format.
 */
@SuppressWarnings("serial")
public class SellerIdInvalidFormatException extends DomainException {

/**
 * Constructs a new SellerIdInvalidFormatException.
 */
public SellerIdInvalidFormatException() {
        super("Seller ID is not a valid UUID");
    }

}

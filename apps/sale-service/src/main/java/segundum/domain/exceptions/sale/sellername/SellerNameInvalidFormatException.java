package segundum.domain.exceptions.sale.sellername;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller name has an invalid format.
 */
@SuppressWarnings("serial")
public class SellerNameInvalidFormatException extends DomainException {

/**
 * Constructs a new SellerNameInvalidFormatException.
 */
public SellerNameInvalidFormatException() {
        super("Seller name is not alphabetic");
    }

}

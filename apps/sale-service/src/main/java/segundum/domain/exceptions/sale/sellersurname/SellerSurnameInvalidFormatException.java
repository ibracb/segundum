package segundum.domain.exceptions.sale.sellersurname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller surname has an invalid format.
 */
@SuppressWarnings("serial")
public class SellerSurnameInvalidFormatException extends DomainException {

/**
 * Constructs a new SellerSurnameInvalidFormatException.
 */
public SellerSurnameInvalidFormatException() {
        super("Seller surname is not alphabetic");
    }

}

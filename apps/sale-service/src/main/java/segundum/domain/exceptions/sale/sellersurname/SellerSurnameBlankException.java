package segundum.domain.exceptions.sale.sellersurname;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the seller surname is blank.
 */
@SuppressWarnings("serial")
public class SellerSurnameBlankException extends DomainException {

/**
 * Constructs a new SellerSurnameBlankException.
 */
public SellerSurnameBlankException() {
        super("Seller surname is blank");
    }

}

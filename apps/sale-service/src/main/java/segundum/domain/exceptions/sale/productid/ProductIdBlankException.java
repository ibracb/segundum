package segundum.domain.exceptions.sale.productid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the product id is blank.
 */
@SuppressWarnings("serial")
public class ProductIdBlankException extends DomainException {

/**
 * Constructs a new ProductIdBlankException.
 */
public ProductIdBlankException() {
        super("Product ID cannot be blank");
    }

}

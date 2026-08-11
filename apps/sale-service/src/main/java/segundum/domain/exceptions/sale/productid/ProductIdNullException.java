package segundum.domain.exceptions.sale.productid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the product id is null.
 */
@SuppressWarnings("serial")
public class ProductIdNullException extends DomainException {

/**
 * Constructs a new ProductIdNullException.
 */
public ProductIdNullException() {
        super("Product ID cannot be null");
    }

}

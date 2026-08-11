package segundum.domain.exceptions.sale.productid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the product id has an invalid format.
 */
@SuppressWarnings("serial")
public class ProductIdInvalidFormatException extends DomainException {

/**
 * Constructs a new ProductIdInvalidFormatException.
 */
public ProductIdInvalidFormatException() {
        super("Product ID is not a valid UUID");
    }

}

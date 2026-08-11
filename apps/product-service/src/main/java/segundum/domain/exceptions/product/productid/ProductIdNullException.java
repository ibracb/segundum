package segundum.domain.exceptions.product.productid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a product ID is null.
 */
@SuppressWarnings("serial")
public class ProductIdNullException extends DomainException {

    /**
     * Constructs a new ProductIdNullException with a default message.
     */
    public ProductIdNullException() {
        super("Product ID cannot be null");
    }

}

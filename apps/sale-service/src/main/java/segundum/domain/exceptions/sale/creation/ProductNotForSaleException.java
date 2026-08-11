package segundum.domain.exceptions.sale.creation;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the product is not for sale.
 */
@SuppressWarnings("serial")
public class ProductNotForSaleException extends DomainException {

/**
 * Constructs a new ProductNotForSaleException.
 */
public ProductNotForSaleException() {
        super("Product is not for sale");
    }

}

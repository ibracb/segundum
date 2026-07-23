package segundum.domain.exceptions.product.salestatus;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when trying to perform an operation on a product
 * that must be on sale but is not.
 */
@SuppressWarnings("serial")
public class ProductNotForSaleException extends DomainException {

		/**
	 * Constructs a new ProductNotOnSaleException with a default message.
	 */
	public ProductNotForSaleException() {
		super("Product must be on sale");
	}

}

package segundum.domain.exceptions.product.salestatus;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when trying to perform an operation on a product
 * that must be in draft status but is not.
 */
@SuppressWarnings("serial")
public class ProductNotDraftException extends DomainException {

		/**
	 * Constructs a new ProductNotDraftException with a default message.
	 */
	public ProductNotDraftException() {
		super("Product must be in draft status");
	}

}

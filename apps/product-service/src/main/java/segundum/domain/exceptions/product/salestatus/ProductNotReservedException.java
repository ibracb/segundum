package segundum.domain.exceptions.product.salestatus;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when trying to perform an operation on a product
 * that must be in reserved status but is not.
 */
@SuppressWarnings("serial")
public class ProductNotReservedException extends DomainException {

		/**
	 * Constructs a new ProductNotReservedException with a default message.
	 */
	public ProductNotReservedException() {
		super("Product must be in reserved status");
	}

}

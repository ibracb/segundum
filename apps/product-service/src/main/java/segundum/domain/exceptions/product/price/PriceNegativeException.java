package segundum.domain.exceptions.product.price;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a product price is negative.
 */
@SuppressWarnings("serial")
public class PriceNegativeException extends DomainException {

		/**
	 * Constructs a new PriceNegativeException with a default message.
	 */
	public PriceNegativeException() {
		super("Product price cannot be negative");
	}

}

package segundum.domain.exceptions.product.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a product description is null.
 */
@SuppressWarnings("serial")
public class DescriptionNullException extends DomainException {

		/**
	 * Constructs a new DescriptionNullException with a default message.
	 */
	public DescriptionNullException() {
		super("Product description cannot be null");
	}

}

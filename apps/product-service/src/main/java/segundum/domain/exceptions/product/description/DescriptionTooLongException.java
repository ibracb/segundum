package segundum.domain.exceptions.product.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a product description exceeds the maximum allowed length.
 */
@SuppressWarnings("serial")
public class DescriptionTooLongException extends DomainException {

		/**
	 * Constructs a new DescriptionTooLongException with a default message.
	 */
	public DescriptionTooLongException() {
		super("Product description cannot exceed 2000 characters");
	}

}

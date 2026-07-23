package segundum.domain.exceptions.product.description;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a product description is blank or empty.
 */
@SuppressWarnings("serial")
public class DescriptionBlankException extends DomainException {

		/**
	 * Constructs a new DescriptionBlankException with a default message.
	 */
	public DescriptionBlankException() {
		super("Product description cannot be blank");
	}

}

package segundum.domain.exceptions.product.title;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a product title is blank or empty.
 */
@SuppressWarnings("serial")
public class TitleBlankException extends DomainException {

		/**
	 * Constructs a new TitleBlankException with a default message.
	 */
	public TitleBlankException() {
		super("Product title cannot be blank");
	}

}

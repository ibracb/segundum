package segundum.domain.exceptions.product.title;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a product title exceeds the maximum allowed length.
 */
@SuppressWarnings("serial")
public class TitleTooLongException extends DomainException {

		/**
	 * Constructs a new TitleTooLongException with a default message.
	 */
	public TitleTooLongException() {
		super("Product title cannot exceed 200 characters");
	}

}

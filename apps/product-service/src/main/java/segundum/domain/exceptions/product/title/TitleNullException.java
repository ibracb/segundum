package segundum.domain.exceptions.product.title;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a product title is null.
 */
@SuppressWarnings("serial")
public class TitleNullException extends DomainException {

		/**
	 * Constructs a new TitleNullException with a default message.
	 */
	public TitleNullException() {
		super("Product title cannot be null");
	}

}

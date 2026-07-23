package segundum.domain.exceptions.category.categoryid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category ID is not numeric.
 */
@SuppressWarnings("serial")
public class CategoryIdInvalidFormatException extends DomainException {

	/**
	 * Constructs a new CategoryIdInvalidFormatException with a default message.
	 */
	public CategoryIdInvalidFormatException() {
		super("Category ID must be a numeric value");
	}

}

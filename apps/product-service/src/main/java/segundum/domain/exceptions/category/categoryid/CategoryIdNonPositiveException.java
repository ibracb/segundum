package segundum.domain.exceptions.category.categoryid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category ID is not positive (less than or equal to 0).
 */
@SuppressWarnings("serial")
public class CategoryIdNonPositiveException extends DomainException {

	/**
	 * Constructs a new CategoryIdNonPositiveException with a default message.
	 */
	public CategoryIdNonPositiveException() {
		super("Category ID must be a positive number greater than 0");
	}

}

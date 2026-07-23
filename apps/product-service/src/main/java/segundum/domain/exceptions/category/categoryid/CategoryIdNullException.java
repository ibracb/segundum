package segundum.domain.exceptions.category.categoryid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category ID is null.
 */
@SuppressWarnings("serial")
public class CategoryIdNullException extends DomainException {

	/**
	 * Constructs a new CategoryIdNullException with a message indicating that the category ID cannot be null.
	 */
	public CategoryIdNullException() {
		super("Category ID cannot be null");
	}

}

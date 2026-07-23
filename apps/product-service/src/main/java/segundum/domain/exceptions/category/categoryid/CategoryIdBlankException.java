package segundum.domain.exceptions.category.categoryid;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category ID is blank or empty.
 */
@SuppressWarnings("serial")
public class CategoryIdBlankException extends DomainException {

	/**
	 * Constructs a new CategoryIdBlankException with a default message.
	 */
	public CategoryIdBlankException() {
		super("Category ID cannot be blank");
	}

}

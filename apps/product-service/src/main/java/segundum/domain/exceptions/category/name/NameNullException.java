package segundum.domain.exceptions.category.name;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category name is null.
 */
@SuppressWarnings("serial")
public class NameNullException extends DomainException {

	/**
	 * Constructs a new NameNullException with a message indicating that the category name cannot be null.
	 */
	public NameNullException() {
		super("Category name cannot be null");
	}

}

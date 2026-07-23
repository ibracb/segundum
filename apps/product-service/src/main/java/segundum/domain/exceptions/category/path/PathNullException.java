package segundum.domain.exceptions.category.path;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category path is null.
 */
@SuppressWarnings("serial")
public class PathNullException extends DomainException {

	/**
	 * Constructs a new PathNullException with a message indicating that the category path cannot be null.
	 */
	public PathNullException() {
		super("Category path cannot be null");
	}

}

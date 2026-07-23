package segundum.domain.exceptions.category.path;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category path is blank or empty.
 */
@SuppressWarnings("serial")
public class PathBlankException extends DomainException {

	/**
	 * Constructs a new PathBlankException with a default message.
	 */
	public PathBlankException() {
		super("Category path cannot be blank");
	}

}

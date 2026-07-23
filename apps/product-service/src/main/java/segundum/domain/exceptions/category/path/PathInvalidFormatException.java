package segundum.domain.exceptions.category.path;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a category path does not follow the expected format |id|id|...
 */
@SuppressWarnings("serial")
public class PathInvalidFormatException extends DomainException {

	/**
	 * Constructs a new PathInvalidFormatException with a default message.
	 */
	public PathInvalidFormatException() {
		super("Category path must follow the format |id|id|... where each id is numeric");
	}

}

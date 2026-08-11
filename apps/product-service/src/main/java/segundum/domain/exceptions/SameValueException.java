package segundum.domain.exceptions;

/**
 * Exception thrown when a new value is the same as the current value.
 */
@SuppressWarnings("serial")
public class SameValueException extends DomainException {

	/**
	 * Constructs a new SameValueException for the given field.
	 *
	 * @param field the field whose value is the same
	 */
	public SameValueException(String field) {
		super("The new " + field + " cannot be the same as the current one.");
	}

}

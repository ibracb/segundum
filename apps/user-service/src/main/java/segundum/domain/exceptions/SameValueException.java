package segundum.domain.exceptions;

/**
 * Exception thrown when a new value is the same as the current value for a field.
 */
@SuppressWarnings("serial")
public class SameValueException extends DomainException {
	
	/**
	 * Constructs a new SameValueException with a message indicating that the new value for the specified field is the
	 * same as the current value.
	 * 
	 * @param field the name of the field for which the new value is the same as the current value
	 */
	public SameValueException(String field) { 
        super("The new " + field + " cannot be the same as the current one."); 
    }

}

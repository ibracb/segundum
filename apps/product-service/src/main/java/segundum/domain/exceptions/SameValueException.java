package segundum.domain.exceptions;

@SuppressWarnings("serial")
public class SameValueException extends DomainException {

	public SameValueException(String field) {
		super("The new " + field + " cannot be the same as the current one.");
	}

}

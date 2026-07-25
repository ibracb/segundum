package segundum.domain.models.user;

import segundum.domain.exceptions.user.email.EmailBlankException;
import segundum.domain.exceptions.user.email.EmailInvalidFormatException;
import segundum.domain.exceptions.user.email.EmailNullException;

/**
 * Represents a user's email address.
 */
public class Email {
	
	/**
	 * Regular expression to ensure that the email address is in a valid format.
	 */
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	
	/**
	 * The value of the email address.
	 */
	private final String value;
	
	/**
	 * Constructs a new Email object with the given value.
	 * 
	 * @param value the email address value
	 * @throws EmailNullException if the value is null
	 * @throws EmailBlankException if the value is blank
	 * @throws EmailInvalidFormatException if the value is not in a valid email format
	 */
	public Email(String value) {
		ensureIsNotNull(value);
		ensureIsNotBlank(value);
		ensureIsValidEmail(value);
		this.value = value;
	}
	
	/**
	 * Ensures that the given value is not null.
	 * 
	 * @param value the value to ensure
	 * @throws EmailNullException if the value is null
	 */
	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new EmailNullException();
		}
	}
	
	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 * 
	 * @param value the value to ensure
	 * @throws EmailBlankException if the value is blank
	 */
	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new EmailBlankException();
		}
	}
	
	/**
	 * Ensures that the given value is in a valid email format.
	 * 
	 * @param value the value to ensure
	 * @throws EmailInvalidFormatException if the value is not in a valid email format
	 */
	private static void ensureIsValidEmail(String value) {
		if (!value.matches(EMAIL_REGEX)) {
			throw new EmailInvalidFormatException();
		}
	}
	
	/**
	 * Returns the value of the email address.
	 * 
	 * @return the email address value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Email email = (Email) o;
		return value.equals(email.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}

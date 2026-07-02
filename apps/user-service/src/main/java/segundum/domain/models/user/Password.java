package segundum.domain.models.user;

import segundum.domain.exceptions.password.PasswordBlankException;
import segundum.domain.exceptions.password.PasswordNullException;
import segundum.domain.exceptions.password.PasswordTooLongException;
import segundum.domain.exceptions.password.PasswordTooShortException;

/**
 * Represents a user's password.
 */
public class Password {
	
	/**
	 * Minimum length for a valid password.
	 */
	private static final int MIN_LENGTH = 8;
	
	/**
	 * Maximum length for a valid password.
	 */
	private static final int MAX_LENGTH = 64;
	
	/**
	 * The value of the password.
	 */
	private final String value;
	
	/**
	 * Constructs a new Password object with the given value.
	 * 
	 * @param value the password value
	 * @throws PasswordNullException if the value is null
	 * @throws PasswordBlankException if the value is blank
	 * @throws PasswordTooShortException if the value is too short
	 * @throws PasswordTooLongException if the value is too long
	 */
	public Password(String value) {
		ensureIsNotNull(value);
		ensureIsNotBlank(value);
		ensureisNotTooShort(value);
		ensureisNotTooLong(value);
		this.value = value;
	}
	
	/**
	 * Ensures that the given value is not null.
	 * 
	 * @param value the value to ensure
	 * @throws PasswordNullException if the value is null
	 */
	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new PasswordNullException();
		}
	}
	
	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 * 
	 * @param value the value to ensure
	 * @throws PasswordBlankException if the value is blank
	 */
	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new PasswordBlankException();
		}
	}
	
	/**
	 * Ensures that the given value is not too short.
	 * 
	 * @param value the value to ensure
	 * @throws PasswordTooShortException if the value is too short
	 */
	private static void ensureisNotTooShort(String value) {
		if (value.length() < MIN_LENGTH) {
			throw new PasswordTooShortException();
		}
	}
	
	/**
	 * Ensures that the given value is not too long.
	 * 
	 * @param value the value to ensure
	 * @throws PasswordTooLongException if the value is too long
	 */
	private static void ensureisNotTooLong(String value) {
		if (value.length() > MAX_LENGTH) {
			throw new PasswordTooLongException();
		}
	}
	
	/**
	 * Returns the value of the password.
	 * 
	 * @return the password value
	 */
	public String getValue() {
		return value;
	}

}

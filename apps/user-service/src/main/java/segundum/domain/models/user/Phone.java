package segundum.domain.models.user;

import segundum.domain.exceptions.user.phone.PhoneBlankException;
import segundum.domain.exceptions.user.phone.PhoneInvalidFormatException;
import segundum.domain.exceptions.user.phone.PhoneNullException;

/**
 * Represents a user's phone number.
 */
public class Phone {
	
	/**
	 * Regular expression to ensure that the phone number is in E.164 format.
	 */
	private static final String E164_REGEX = "^\\+[1-9]\\d{1,14}$";
	
	/**
	 * The value of the phone number.
	 */
	private final String value;
	
	/**
	 * Constructs a new Phone object with the given value.
	 * 
	 * @param value the phone number value
	 * @throws PhoneNullException if the value is null
	 * @throws PhoneBlankException if the value is blank
	 * @throws PhoneInvalidFormatException if the value is not in E.164 format
	 */
	public Phone(String value) {
		ensureIsNotNull(value);
		ensureIsNotBlank(value);
		ensureIsValidPhone(value);
		this.value = value;
	}
	
	/**
	 * Ensures that the given value is not null.
	 * 
	 * @param value the value to ensure
	 * @throws PhoneNullException if the value is null
	 */
	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new PhoneNullException();
		}
	}
	
	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 * 
	 * @param value the value to ensure
	 * @throws PhoneBlankException if the value is blank
	 */
	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new PhoneBlankException();
		}
	}
	
	/**
	 * Ensures that the given value is in E.164 format.
	 * 
	 * @param value the value to ensure
	 * @throws PhoneInvalidFormatException if the value is not in E.164 format
	 */
	private static void ensureIsValidPhone(String value) {
		if (!value.matches(E164_REGEX)) {
			throw new PhoneInvalidFormatException();
		}
	}
	
	/**
	 * Returns the value of the phone number.
	 * 
	 * @return the phone number value
	 */
	public String getValue() {
		return value;
	}

}

package segundum.domain.models.category;

import segundum.domain.exceptions.category.name.NameBlankException;
import segundum.domain.exceptions.category.name.NameInvalidFormatException;
import segundum.domain.exceptions.category.name.NameNullException;

/**
 * Represents a category's name.
 */
public class Name {

	/**
	 * Regular expression to ensure that the name contains only letters, digits, spaces,
	 * and common punctuation characters (commas, hyphens, parentheses, slashes, periods).
	 */
	private static final String NAME_REGEX = "^[\\p{L}\\d\\s,\\-\\(\\)\\/\\.]+$";

	/**
	 * The value of the name.
	 */
	private final String value;

	/**
	 * Constructs a new Name object with the given value.
	 *
	 * @param value the name value
	 * @throws NameNullException if the value is null
	 * @throws NameBlankException if the value is blank
	 * @throws NameInvalidFormatException if the value contains non-alphabetic characters
	 */
	public Name(String value) {
		ensureIsNotNull(value);
		ensureIsNotBlank(value);
		ensureIsAlphabetic(value);
		this.value = value;
	}

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws NameNullException if the value is null
	 */
	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new NameNullException();
		}
	}

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws NameBlankException if the value is blank
	 */
	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new NameBlankException();
		}
	}

	/**
	 * Ensures that the name contains only allowed characters (letters, digits, spaces,
	 * commas, hyphens, parentheses, slashes, and periods).
	 *
	 * @param value the value to ensure
	 * @throws NameInvalidFormatException if the value contains disallowed characters
	 */
	private static void ensureIsAlphabetic(String value) {
		if (!value.matches(NAME_REGEX)) {
			throw new NameInvalidFormatException();
		}
	}

	/**
	 * Returns the value of the name.
	 *
	 * @return the name value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Name name = (Name) o;
		return value.equals(name.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}

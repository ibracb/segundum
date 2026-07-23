package segundum.domain.models.category;

import segundum.domain.exceptions.category.description.DescriptionBlankException;
import segundum.domain.exceptions.category.description.DescriptionTooLongException;

/**
 * Represents a category's description.
 * This value object is nullable, but if provided, it cannot be blank and must not exceed 500 characters.
 */
public class Description {

	/**
	 * The maximum allowed length for the description.
	 */
	private static final int MAX_LENGTH = 500;

	/**
	 * The value of the description.
	 */
	private final String value;

	/**
	 * Constructs a new Description object with the given value.
	 *
	 * @param value the description value (nullable)
	 * @throws DescriptionBlankException if the value is not null but is blank
	 * @throws DescriptionTooLongException if the value exceeds 500 characters
	 */
	public Description(String value) {
		if (value != null) {
			ensureIsNotBlank(value);
			ensureIsWithinMaxLength(value);
		}
		this.value = value;
	}

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws DescriptionBlankException if the value is blank
	 */
	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new DescriptionBlankException();
		}
	}

	/**
	 * Ensures that the given value does not exceed the maximum allowed length.
	 *
	 * @param value the value to ensure
	 * @throws DescriptionTooLongException if the value exceeds 500 characters
	 */
	private static void ensureIsWithinMaxLength(String value) {
		if (value.length() > MAX_LENGTH) {
			throw new DescriptionTooLongException();
		}
	}

	/**
	 * Returns the value of the description.
	 *
	 * @return the description value (may be null)
	 */
	public String getValue() {
		return value;
	}

}

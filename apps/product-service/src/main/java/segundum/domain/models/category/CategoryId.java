package segundum.domain.models.category;

import segundum.domain.exceptions.category.categoryid.CategoryIdBlankException;
import segundum.domain.exceptions.category.categoryid.CategoryIdInvalidFormatException;
import segundum.domain.exceptions.category.categoryid.CategoryIdNullException;
import segundum.domain.exceptions.category.categoryid.CategoryIdNonPositiveException;

/**
 * Represents a category's unique identifier.
 * This is obtained from XML files and is not generated locally.
 */
public class CategoryId {

	/**
	 * Regular expression to ensure that the category ID contains only digits.
	 */
	private static final String NUMERIC_REGEX = "^-?\\d+$";

	/**
	 * The value of the unique identifier.
	 */
	private final String value;

	/**
	 * Constructs a new CategoryId object with the given value.
	 *
	 * @param value the category ID value
	 * @throws CategoryIdNullException if the value is null
	 * @throws CategoryIdBlankException if the value is blank
	 * @throws CategoryIdInvalidFormatException if the value is not numeric
	 * @throws CategoryIdNonPositiveException if the value is not positive (less than or equal to 0)
	 */
	private CategoryId(String value) {
		ensureIsNotNull(value);
		ensureIsNotBlank(value);
		ensureIsNumeric(value);
		ensureIsPositive(value);
		this.value = value;
	}

	/**
	 * Creates a new CategoryId from a String value.
	 *
	 * @param value the category ID value
	 * @return a new CategoryId object
	 */
	public static CategoryId fromString(String value) {
		return new CategoryId(value);
	}

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws CategoryIdNullException if the value is null
	 */
	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new CategoryIdNullException();
		}
	}

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws CategoryIdBlankException if the value is blank
	 */
	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new CategoryIdBlankException();
		}
	}

	/**
	 * Ensures that the given value contains only digits.
	 *
	 * @param value the value to ensure
	 * @throws CategoryIdInvalidFormatException if the value is not numeric
	 */
	private static void ensureIsNumeric(String value) {
		if (!value.matches(NUMERIC_REGEX)) {
			throw new CategoryIdInvalidFormatException();
		}
	}

	/**
	 * Ensures that the given value is positive (greater than 0).
	 *
	 * @param value the value to ensure
	 * @throws CategoryIdNonPositiveException if the value is not positive
	 */
	private static void ensureIsPositive(String value) {
		if (Long.parseLong(value) <= 0) {
			throw new CategoryIdNonPositiveException();
		}
	}

	/**
	 * Returns the value of the unique identifier.
	 *
	 * @return the value of the unique identifier
	 */
	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoryId that = (CategoryId) o;
		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}

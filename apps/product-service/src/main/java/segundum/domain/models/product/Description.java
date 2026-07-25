package segundum.domain.models.product;

import segundum.domain.exceptions.product.description.DescriptionBlankException;
import segundum.domain.exceptions.product.description.DescriptionNullException;
import segundum.domain.exceptions.product.description.DescriptionTooLongException;

/**
 * Represents a product's description.
 */
public class Description {

	/**
	 * The maximum allowed length for the description.
	 */
	private static final int MAX_LENGTH = 2000;

	/**
	 * The value of the description.
	 */
	private final String value;

	/**
	 * Constructs a new Description object with the given value.
	 *
	 * @param value the description value
	 * @throws DescriptionNullException if the value is null
	 * @throws DescriptionBlankException if the value is blank
	 * @throws DescriptionTooLongException if the value exceeds 2000 characters
	 */
	public Description(String value) {
		ensureIsNotNull(value);
		ensureIsNotBlank(value);
		ensureIsWithinMaxLength(value);
		this.value = value;
	}

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws DescriptionNullException if the value is null
	 */
	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new DescriptionNullException();
		}
	}

	/**
	 * Ensures that the given value is not blank.
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
	 * @throws DescriptionTooLongException if the value exceeds 2000 characters
	 */
	private static void ensureIsWithinMaxLength(String value) {
		if (value.length() > MAX_LENGTH) {
			throw new DescriptionTooLongException();
		}
	}

	/**
	 * Returns the value of the description.
	 *
	 * @return the description value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Description that = (Description) o;
		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}

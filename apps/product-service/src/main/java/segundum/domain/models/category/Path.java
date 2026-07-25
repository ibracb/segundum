package segundum.domain.models.category;

import segundum.domain.exceptions.category.path.PathBlankException;
import segundum.domain.exceptions.category.path.PathInvalidFormatException;
import segundum.domain.exceptions.category.path.PathNullException;

/**
 * Represents a category's path in the hierarchy.
 * The path format is |id1|id2|... where each id is numeric.
 */
public class Path {

	/**
	 * Regular expression to ensure that the path follows the format |id|id|... where each id is numeric.
	 */
	private static final String PATH_REGEX = "^\\|(\\d+\\|)+$";

	/**
	 * The value of the path.
	 */
	private final String value;

	/**
	 * Constructs a new Path object with the given value.
	 *
	 * @param value the path value
	 * @throws PathNullException if the value is null
	 * @throws PathBlankException if the value is blank
	 * @throws PathInvalidFormatException if the value does not follow the expected format
	 */
	public Path(String value) {
		ensureIsNotNull(value);
		ensureIsNotBlank(value);
		ensureIsValidFormat(value);
		this.value = value;
	}

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws PathNullException if the value is null
	 */
	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new PathNullException();
		}
	}

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws PathBlankException if the value is blank
	 */
	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new PathBlankException();
		}
	}

	/**
	 * Ensures that the given value follows the format |id|id|... where each id is numeric.
	 *
	 * @param value the value to ensure
	 * @throws PathInvalidFormatException if the value does not follow the expected format
	 */
	private static void ensureIsValidFormat(String value) {
		if (!value.matches(PATH_REGEX)) {
			throw new PathInvalidFormatException();
		}
	}

	/**
	 * Returns the value of the path.
	 *
	 * @return the path value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Path path = (Path) o;
		return value.equals(path.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}

package segundum.domain.models.sale;

import segundum.domain.exceptions.sale.title.TitleBlankException;
import segundum.domain.exceptions.sale.title.TitleNullException;
import segundum.domain.exceptions.sale.title.TitleTooLongException;

/**
 * Represents the title of a sale.
 */
public class Title {

	/**
	 * The maximum allowed length for the title.
	 */
    private static final int MAX_LENGTH = 200;

	/**
	 * The value of the title.
	 */
    private final String value;

	/**
	 * Constructs a new Title object with the given value.
	 *
	 * @param value the title value
	 * @throws TitleNullException if the value is null
	 * @throws TitleBlankException if the value is blank
	 * @throws TitleTooLongException if the value exceeds the maximum length
	 */
    public Title(String value) {
        ensureIsNotNull(value);
        ensureIsNotBlank(value);
        ensureIsWithinMaxLength(value);
        this.value = value;
    }

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws TitleNullException if the value is null
	 */
    private static void ensureIsNotNull(String value) {
        if (value == null) {
            throw new TitleNullException();
        }
    }

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws TitleBlankException if the value is blank
	 */
    private static void ensureIsNotBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new TitleBlankException();
        }
    }

	/**
	 * Ensures that the given value does not exceed the maximum length.
	 *
	 * @param value the value to ensure
	 * @throws TitleTooLongException if the value exceeds the maximum length
	 */
    private static void ensureIsWithinMaxLength(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new TitleTooLongException();
        }
    }

	/**
	 * Returns the value of the title.
	 *
	 * @return the value of the title
	 */
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
        return value.equals(title.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

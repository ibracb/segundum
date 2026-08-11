package segundum.domain.models.sale;

import segundum.domain.exceptions.sale.sellername.SellerNameBlankException;
import segundum.domain.exceptions.sale.sellername.SellerNameInvalidFormatException;
import segundum.domain.exceptions.sale.sellername.SellerNameNullException;

/**
 * Represents the name of a seller in a sale.
 */
public class SellerName {

	/**
	 * Regular expression to ensure that the name contains only letters and spaces.
	 */
    private static final String NAME_REGEX = "^[\\p{L}\\s]+$";

	/**
	 * The value of the name.
	 */
    private final String value;

	/**
	 * Constructs a new SellerName object with the given value.
	 *
	 * @param value the name value
	 * @throws SellerNameNullException if the value is null
	 * @throws SellerNameBlankException if the value is blank
	 * @throws SellerNameInvalidFormatException if the value is not alphabetic
	 */
    public SellerName(String value) {
        ensureIsNotNull(value);
        ensureIsNotBlank(value);
        ensureIsAlphabetic(value);
        this.value = value;
    }

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws SellerNameNullException if the value is null
	 */
    private static void ensureIsNotNull(String value) {
        if (value == null) {
            throw new SellerNameNullException();
        }
    }

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws SellerNameBlankException if the value is blank
	 */
    private static void ensureIsNotBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new SellerNameBlankException();
        }
    }

	/**
	 * Ensures that the given value contains only letters and spaces.
	 *
	 * @param value the value to ensure
	 * @throws SellerNameInvalidFormatException if the value is not alphabetic
	 */
    private static void ensureIsAlphabetic(String value) {
        if (!value.matches(NAME_REGEX)) {
            throw new SellerNameInvalidFormatException();
        }
    }

	/**
	 * Returns the value of the name.
	 *
	 * @return the value of the name
	 */
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerName sellerName = (SellerName) o;
        return value.equals(sellerName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

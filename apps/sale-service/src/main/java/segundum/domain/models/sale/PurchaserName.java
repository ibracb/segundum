package segundum.domain.models.sale;

import segundum.domain.exceptions.sale.purchasername.PurchaserNameBlankException;
import segundum.domain.exceptions.sale.purchasername.PurchaserNameInvalidFormatException;
import segundum.domain.exceptions.sale.purchasername.PurchaserNameNullException;

/**
 * Represents the name of a purchaser in a sale.
 */
public class PurchaserName {

	/**
	 * Regular expression to ensure that the name contains only letters and spaces.
	 */
    private static final String NAME_REGEX = "^[\\p{L}\\s]+$";

	/**
	 * The value of the name.
	 */
    private final String value;

	/**
	 * Constructs a new PurchaserName object with the given value.
	 *
	 * @param value the name value
	 * @throws PurchaserNameNullException if the value is null
	 * @throws PurchaserNameBlankException if the value is blank
	 * @throws PurchaserNameInvalidFormatException if the value is not alphabetic
	 */
    public PurchaserName(String value) {
        ensureIsNotNull(value);
        ensureIsNotBlank(value);
        ensureIsAlphabetic(value);
        this.value = value;
    }

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws PurchaserNameNullException if the value is null
	 */
    private static void ensureIsNotNull(String value) {
        if (value == null) {
            throw new PurchaserNameNullException();
        }
    }

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws PurchaserNameBlankException if the value is blank
	 */
    private static void ensureIsNotBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new PurchaserNameBlankException();
        }
    }

	/**
	 * Ensures that the given value contains only letters and spaces.
	 *
	 * @param value the value to ensure
	 * @throws PurchaserNameInvalidFormatException if the value is not alphabetic
	 */
    private static void ensureIsAlphabetic(String value) {
        if (!value.matches(NAME_REGEX)) {
            throw new PurchaserNameInvalidFormatException();
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
        PurchaserName purchaserName = (PurchaserName) o;
        return value.equals(purchaserName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

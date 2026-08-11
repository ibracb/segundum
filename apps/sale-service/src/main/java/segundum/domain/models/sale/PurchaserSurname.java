package segundum.domain.models.sale;

import segundum.domain.exceptions.sale.purchasersurname.PurchaserSurnameBlankException;
import segundum.domain.exceptions.sale.purchasersurname.PurchaserSurnameInvalidFormatException;
import segundum.domain.exceptions.sale.purchasersurname.PurchaserSurnameNullException;

/**
 * Represents the surname of a purchaser in a sale.
 */
public class PurchaserSurname {

	/**
	 * Regular expression to ensure that the surname contains only letters and spaces.
	 */
    private static final String SURNAME_REGEX = "^[\\p{L}\\s]+$";

	/**
	 * The value of the surname.
	 */
    private final String value;

	/**
	 * Constructs a new PurchaserSurname object with the given value.
	 *
	 * @param value the surname value
	 * @throws PurchaserSurnameNullException if the value is null
	 * @throws PurchaserSurnameBlankException if the value is blank
	 * @throws PurchaserSurnameInvalidFormatException if the value is not alphabetic
	 */
    public PurchaserSurname(String value) {
        ensureIsNotNull(value);
        ensureIsNotBlank(value);
        ensureIsAlphabetic(value);
        this.value = value;
    }

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws PurchaserSurnameNullException if the value is null
	 */
    private static void ensureIsNotNull(String value) {
        if (value == null) {
            throw new PurchaserSurnameNullException();
        }
    }

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws PurchaserSurnameBlankException if the value is blank
	 */
    private static void ensureIsNotBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new PurchaserSurnameBlankException();
        }
    }

	/**
	 * Ensures that the given value contains only letters and spaces.
	 *
	 * @param value the value to ensure
	 * @throws PurchaserSurnameInvalidFormatException if the value is not alphabetic
	 */
    private static void ensureIsAlphabetic(String value) {
        if (!value.matches(SURNAME_REGEX)) {
            throw new PurchaserSurnameInvalidFormatException();
        }
    }

	/**
	 * Returns the value of the surname.
	 *
	 * @return the value of the surname
	 */
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaserSurname purchaserSurname = (PurchaserSurname) o;
        return value.equals(purchaserSurname.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

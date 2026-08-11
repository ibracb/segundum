package segundum.domain.models.sale;

import segundum.domain.exceptions.sale.sellersurname.SellerSurnameBlankException;
import segundum.domain.exceptions.sale.sellersurname.SellerSurnameInvalidFormatException;
import segundum.domain.exceptions.sale.sellersurname.SellerSurnameNullException;

/**
 * Represents the surname of a seller in a sale.
 */
public class SellerSurname {

	/**
	 * Regular expression to ensure that the surname contains only letters and spaces.
	 */
    private static final String SURNAME_REGEX = "^[\\p{L}\\s]+$";

	/**
	 * The value of the surname.
	 */
    private final String value;

	/**
	 * Constructs a new SellerSurname object with the given value.
	 *
	 * @param value the surname value
	 * @throws SellerSurnameNullException if the value is null
	 * @throws SellerSurnameBlankException if the value is blank
	 * @throws SellerSurnameInvalidFormatException if the value is not alphabetic
	 */
    public SellerSurname(String value) {
        ensureIsNotNull(value);
        ensureIsNotBlank(value);
        ensureIsAlphabetic(value);
        this.value = value;
    }

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws SellerSurnameNullException if the value is null
	 */
    private static void ensureIsNotNull(String value) {
        if (value == null) {
            throw new SellerSurnameNullException();
        }
    }

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws SellerSurnameBlankException if the value is blank
	 */
    private static void ensureIsNotBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new SellerSurnameBlankException();
        }
    }

	/**
	 * Ensures that the given value contains only letters and spaces.
	 *
	 * @param value the value to ensure
	 * @throws SellerSurnameInvalidFormatException if the value is not alphabetic
	 */
    private static void ensureIsAlphabetic(String value) {
        if (!value.matches(SURNAME_REGEX)) {
            throw new SellerSurnameInvalidFormatException();
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
        SellerSurname sellerSurname = (SellerSurname) o;
        return value.equals(sellerSurname.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

package segundum.domain.models.seller;

import segundum.domain.exceptions.seller.surname.SurnameBlankException;
import segundum.domain.exceptions.seller.surname.SurnameInvalidFormatException;
import segundum.domain.exceptions.seller.surname.SurnameNullException;

/**
 * Represents a seller's surname.
 * This is a replica of the user surname from the user microservice, received via domain events.
 */
public class Surname {

	/**
	 * Regular expression to ensure that the surname contains only letters and spaces.
	 */
	private static final String SURNAME_REGEX = "^[\\p{L}\\s]+$";

	/**
	 * The value of the surname.
	 */
	private final String value;

	/**
	 * Constructs a new Surname object with the given value.
	 *
	 * @param value the surname value
	 * @throws SurnameNullException if the value is null
	 * @throws SurnameBlankException if the value is blank
	 * @throws SurnameInvalidFormatException if the value contains non-alphabetic characters
	 */
	public Surname(String value) {
		ensureIsNotNull(value);
		ensureIsNotBlank(value);
		ensureIsAlphabetic(value);
		this.value = value;
	}

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws SurnameNullException if the value is null
	 */
	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new SurnameNullException();
		}
	}

	/**
	 * Ensures that the given value is not blank (i.e., not empty or only whitespace).
	 *
	 * @param value the value to ensure
	 * @throws SurnameBlankException if the value is blank
	 */
	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new SurnameBlankException();
		}
	}

	/**
	 * Ensures that the given value contains only alphabetic characters and spaces.
	 *
	 * @param value the value to ensure
	 * @throws SurnameInvalidFormatException if the value contains non-alphabetic characters
	 */
	private static void ensureIsAlphabetic(String value) {
		if (!value.matches(SURNAME_REGEX)) {
			throw new SurnameInvalidFormatException();
		}
	}

	/**
	 * Returns the value of the surname.
	 *
	 * @return the surname value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Surname surname = (Surname) o;
		return value.equals(surname.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}

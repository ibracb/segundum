package segundum.domain.models.user;

import java.time.Instant;

import segundum.domain.exceptions.user.registrationdate.RegistrationDateNullException;

/**
 * Represents the date and time when a user was registered.
 */
public class RegistrationDate {

	/**
	 * The value of the registration date.
	 */
	private final Instant value;

	/**
	 * Constructs a new RegistrationDate with the given value.
	 *
	 * @param value the registration date value
	 */
	private RegistrationDate(Instant value) {
		this.value = value;
	}

	/**
	 * Creates a new RegistrationDate with the current instant.
	 *
	 * @return a new RegistrationDate with the current instant
	 */
	public static RegistrationDate now() {
		return new RegistrationDate(Instant.now());
	}

	/**
	 * Creates a new RegistrationDate from the given instant.
	 *
	 * @param value the instant to create the registration date from
	 * @return a new RegistrationDate with the given instant
	 * @throws RegistrationDateNullException if the value is null
	 */
	public static RegistrationDate fromInstant(Instant value) {
		ensureIsNotNull(value);
		return new RegistrationDate(value);
	}

	/**
	 * Ensures that the given value is not null.
	 *
	 * @param value the value to ensure
	 * @throws RegistrationDateNullException if the value is null
	 */
	private static void ensureIsNotNull(Instant value) {
		if (value == null) {
			throw new RegistrationDateNullException();
		}
	}

	/**
	 * Returns the value of the registration date.
	 *
	 * @return the value of the registration date
	 */
	public Instant getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RegistrationDate that = (RegistrationDate) o;
		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}

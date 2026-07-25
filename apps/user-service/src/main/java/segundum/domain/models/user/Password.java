package segundum.domain.models.user;

import java.util.Objects;

import segundum.domain.exceptions.user.password.PasswordBlankException;
import segundum.domain.exceptions.user.password.PasswordNullException;
import segundum.domain.exceptions.user.password.PasswordTooLongException;
import segundum.domain.exceptions.user.password.PasswordTooShortException;

/**
 * Represents a user's password.
 * <p>
 * A password can be in one of two states:
 * <ul>
 *   <li>{@code plain} — freshly entered by the user, not yet hashed</li>
 *   <li>{@code hashed} — already hashed, read from persistence</li>
 * </ul>
 * Use the static factory methods {@link #plain(String)} and {@link #hashed(String)}
 * instead of the constructor.
 * </p>
 */
public class Password {

	private static final int MIN_LENGTH = 8;
	private static final int MAX_LENGTH = 64;

	private final String value;
	private final boolean hashed;

	/**
	 * Creates a plain-text password from user input.
	 * Validates length, null, and blank constraints.
	 *
	 * @param value the plain-text password
	 * @return a Password in plain state
	 */
	public static Password plain(String value) {
		return new Password(value, false);
	}

	/**
	 * Creates a password from an already-hashed value.
	 * No validation is applied since the hash was previously validated.
	 *
	 * @param value the hashed password
	 * @return a Password in hashed state
	 */
	public static Password hashed(String value) {
		return new Password(value, true);
	}

	private Password(String value, boolean hashed) {
		if (!hashed) {
			ensureIsNotNull(value);
			ensureIsNotBlank(value);
			ensureisNotTooShort(value);
			ensureisNotTooLong(value);
		}
		this.value = value;
		this.hashed = hashed;
	}

	private static void ensureIsNotNull(String value) {
		if (value == null) {
			throw new PasswordNullException();
		}
	}

	private static void ensureIsNotBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new PasswordBlankException();
		}
	}

	private static void ensureisNotTooShort(String value) {
		if (value.length() < MIN_LENGTH) {
			throw new PasswordTooShortException();
		}
	}

	private static void ensureisNotTooLong(String value) {
		if (value.length() > MAX_LENGTH) {
			throw new PasswordTooLongException();
		}
	}

	/**
	 * Returns the password value (plain or hashed depending on state).
	 *
	 * @return the password value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Returns whether this password is already hashed.
	 *
	 * @return true if hashed, false if plain-text
	 */
	public boolean isHashed() {
		return hashed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Password password = (Password) o;
		return hashed == password.hashed && value.equals(password.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, hashed);
	}

}

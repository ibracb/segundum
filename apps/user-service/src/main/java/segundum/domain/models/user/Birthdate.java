package segundum.domain.models.user;

import java.time.LocalDate;

import segundum.domain.exceptions.user.birthdate.BirthdateInFutureException;
import segundum.domain.exceptions.user.birthdate.BirthdateNullException;

/**
 * Represents a user's birthdate.
 */
public class Birthdate {
	
	/**
	 * The value of the birthdate.
	 */
	private final LocalDate value;
	
	/**
	 * Constructs a new Birthdate object with the given value.
	 * 
	 * @param value the birthdate value
	 * @throws BirthdateNullException if the value is null
	 * @throws BirthdateInFutureException if the value is in the future
	 */
	public Birthdate(LocalDate value) {
		ensureIsNotNull(value);
		ensureIsNotInFuture(value);
		this.value = value;
	}
	
	/**
	 * Ensures that the given value is not null.
	 * 
	 * @param value the value to ensure
	 * @throws BirthdateNullException if the value is null
	 */
	private static void ensureIsNotNull(LocalDate value) {
		if (value == null) {
			throw new BirthdateNullException();
		}
	}
	
	/**
	 * Ensures that the given value is not in the future.
	 * 
	 * @param value the value to ensure
	 * @throws BirthdateInFutureException if the value is in the future
	 */
	private static void ensureIsNotInFuture(LocalDate value) {
		if (value.isAfter(LocalDate.now())) {
			throw new BirthdateInFutureException();
		}
	}
	
	/**
	 * Returns the value of the birthdate.
	 * 
	 * @return the value of the birthdate
	 */
	public LocalDate getValue() {
		return value;
	}

}

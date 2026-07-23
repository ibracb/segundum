package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.user.birthdate.BirthdateInFutureException;
import segundum.domain.exceptions.user.birthdate.BirthdateNullException;

class BirthdateTest {

	@Test
	void shouldCreateValidBirthdate() {
		LocalDate date = LocalDate.of(1990, 5, 15);
		Birthdate birthdate = new Birthdate(date);
		assertEquals(date, birthdate.getValue());
	}

	@Test
	void shouldCreateBirthdateForToday() {
		Birthdate birthdate = new Birthdate(LocalDate.now());
		assertEquals(LocalDate.now(), birthdate.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(BirthdateNullException.class, () -> new Birthdate(null));
	}

	@Test
	void shouldThrowWhenInFuture() {
		assertThrows(BirthdateInFutureException.class,
				() -> new Birthdate(LocalDate.now().plusDays(1)));
	}

}

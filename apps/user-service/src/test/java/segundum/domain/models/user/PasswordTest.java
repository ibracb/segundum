package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.password.PasswordBlankException;
import segundum.domain.exceptions.password.PasswordNullException;
import segundum.domain.exceptions.password.PasswordTooLongException;
import segundum.domain.exceptions.password.PasswordTooShortException;

class PasswordTest {

	@Test
	void shouldCreateValidPassword() {
		Password password = new Password("Abcdef123");
		assertEquals("Abcdef123", password.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(PasswordNullException.class, () -> new Password(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(PasswordBlankException.class, () -> new Password(""));
		assertThrows(PasswordBlankException.class, () -> new Password("   "));
	}

	@Test
	void shouldThrowWhenTooShort() {
		assertThrows(PasswordTooShortException.class, () -> new Password("Abc1234"));
	}

	@Test
	void shouldThrowWhenTooLong() {
		assertThrows(PasswordTooLongException.class, () -> new Password("a".repeat(65)));
	}

	@Test
	void shouldAcceptMinimumLength() {
		Password password = new Password("Abcdef12");
		assertEquals("Abcdef12", password.getValue());
	}

	@Test
	void shouldAcceptMaximumLength() {
		String value = "a".repeat(64);
		Password password = new Password(value);
		assertEquals(value, password.getValue());
	}

}

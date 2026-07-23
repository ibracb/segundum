package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.user.password.PasswordBlankException;
import segundum.domain.exceptions.user.password.PasswordNullException;
import segundum.domain.exceptions.user.password.PasswordTooLongException;
import segundum.domain.exceptions.user.password.PasswordTooShortException;

class PasswordTest {

	@Test
	void shouldCreateValidPlainPassword() {
		Password password = Password.plain("Abcdef123");
		assertEquals("Abcdef123", password.getValue());
		assertFalse(password.isHashed());
	}

	@Test
	void shouldCreateHashedPassword() {
		Password password = Password.hashed("$2a$10$xyzhashedvalue");
		assertEquals("$2a$10$xyzhashedvalue", password.getValue());
		assertTrue(password.isHashed());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(PasswordNullException.class, () -> Password.plain(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(PasswordBlankException.class, () -> Password.plain(""));
		assertThrows(PasswordBlankException.class, () -> Password.plain("   "));
	}

	@Test
	void shouldThrowWhenTooShort() {
		assertThrows(PasswordTooShortException.class, () -> Password.plain("Abc1234"));
	}

	@Test
	void shouldThrowWhenTooLong() {
		assertThrows(PasswordTooLongException.class, () -> Password.plain("a".repeat(65)));
	}

	@Test
	void shouldAcceptMinimumLength() {
		Password password = Password.plain("Abcdef12");
		assertEquals("Abcdef12", password.getValue());
	}

	@Test
	void shouldAcceptMaximumLength() {
		String value = "a".repeat(64);
		Password password = Password.plain(value);
		assertEquals(value, password.getValue());
	}

	@Test
	void shouldNotValidateLengthForHashedPassword() {
		Password password = Password.hashed("any-length-hash-value");
		assertEquals("any-length-hash-value", password.getValue());
		assertTrue(password.isHashed());
	}

}

package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.user.email.EmailBlankException;
import segundum.domain.exceptions.user.email.EmailInvalidFormatException;
import segundum.domain.exceptions.user.email.EmailNullException;

class EmailTest {

	@Test
	void shouldCreateValidEmail() {
		Email email = new Email("test@email.com");
		assertEquals("test@email.com", email.getValue());
	}

	@Test
	void shouldAcceptEmailWithSpecialCharacters() {
		Email email = new Email("test.name+tag@email.co.uk");
		assertEquals("test.name+tag@email.co.uk", email.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(EmailNullException.class, () -> new Email(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(EmailBlankException.class, () -> new Email(""));
		assertThrows(EmailBlankException.class, () -> new Email("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(EmailInvalidFormatException.class, () -> new Email("invalid"));
		assertThrows(EmailInvalidFormatException.class, () -> new Email("@.com"));
		assertThrows(EmailInvalidFormatException.class, () -> new Email("user@"));
	}

}

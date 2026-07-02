package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.phone.PhoneBlankException;
import segundum.domain.exceptions.phone.PhoneInvalidFormatException;
import segundum.domain.exceptions.phone.PhoneNullException;

class PhoneTest {

	@Test
	void shouldCreateValidPhone() {
		Phone phone = new Phone("+34612345678");
		assertEquals("+34612345678", phone.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(PhoneNullException.class, () -> new Phone(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(PhoneBlankException.class, () -> new Phone(""));
		assertThrows(PhoneBlankException.class, () -> new Phone("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(PhoneInvalidFormatException.class, () -> new Phone("abc"));
		assertThrows(PhoneInvalidFormatException.class, () -> new Phone("34612345678"));
		assertThrows(PhoneInvalidFormatException.class, () -> new Phone("0"));
	}

}

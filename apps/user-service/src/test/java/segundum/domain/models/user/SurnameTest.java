package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.user.surname.SurnameBlankException;
import segundum.domain.exceptions.user.surname.SurnameInvalidFormatException;
import segundum.domain.exceptions.user.surname.SurnameNullException;

class SurnameTest {

	@Test
	void shouldCreateValidSurname() {
		Surname surname = new Surname("Pérez");
		assertEquals("Pérez", surname.getValue());
	}

	@Test
	void shouldAcceptSurnameWithSpaces() {
		Surname surname = new Surname("De la Torre");
		assertEquals("De la Torre", surname.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(SurnameNullException.class, () -> new Surname(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(SurnameBlankException.class, () -> new Surname(""));
		assertThrows(SurnameBlankException.class, () -> new Surname("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(SurnameInvalidFormatException.class, () -> new Surname("Pérez123"));
		assertThrows(SurnameInvalidFormatException.class, () -> new Surname("Pérez!"));
	}

}

package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.name.NameBlankException;
import segundum.domain.exceptions.name.NameInvalidFormatException;
import segundum.domain.exceptions.name.NameNullException;

class NameTest {

	@Test
	void shouldCreateValidName() {
		Name name = new Name("Juan");
		assertEquals("Juan", name.getValue());
	}

	@Test
	void shouldAcceptNameWithSpaces() {
		Name name = new Name("Juan Carlos");
		assertEquals("Juan Carlos", name.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(NameNullException.class, () -> new Name(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(NameBlankException.class, () -> new Name(""));
		assertThrows(NameBlankException.class, () -> new Name("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(NameInvalidFormatException.class, () -> new Name("Juan123"));
		assertThrows(NameInvalidFormatException.class, () -> new Name("Juan!"));
		assertThrows(NameInvalidFormatException.class, () -> new Name("Juan@"));
	}

}

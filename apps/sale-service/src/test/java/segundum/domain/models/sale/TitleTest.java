package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.title.TitleBlankException;
import segundum.domain.exceptions.sale.title.TitleNullException;
import segundum.domain.exceptions.sale.title.TitleTooLongException;

class TitleTest {

	@Test
	void shouldCreateValidTitle() {
		Title title = new Title("iPhone 13");
		assertEquals("iPhone 13", title.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(TitleNullException.class, () -> new Title(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(TitleBlankException.class, () -> new Title(""));
		assertThrows(TitleBlankException.class, () -> new Title("   "));
	}

	@Test
	void shouldThrowWhenTooLong() {
		assertThrows(TitleTooLongException.class, () -> new Title("a".repeat(201)));
	}

	@Test
	void shouldAcceptMaxLength() {
		assertEquals(200, new Title("a".repeat(200)).getValue().length());
	}

}

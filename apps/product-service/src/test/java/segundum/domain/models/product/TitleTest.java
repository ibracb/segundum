package segundum.domain.models.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.product.title.TitleBlankException;
import segundum.domain.exceptions.product.title.TitleNullException;
import segundum.domain.exceptions.product.title.TitleTooLongException;

class TitleTest {

	@Test
	void shouldCreateValidTitle() {
		Title title = new Title("iPhone 14");
		assertEquals("iPhone 14", title.getValue());
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
		String longTitle = "A".repeat(201);
		assertThrows(TitleTooLongException.class, () -> new Title(longTitle));
	}

	@Test
	void shouldAcceptMaxLength() {
		String maxTitle = "A".repeat(200);
		Title title = new Title(maxTitle);
		assertEquals(200, title.getValue().length());
	}
}

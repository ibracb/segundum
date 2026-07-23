package segundum.domain.models.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.product.description.DescriptionBlankException;
import segundum.domain.exceptions.product.description.DescriptionNullException;
import segundum.domain.exceptions.product.description.DescriptionTooLongException;

class DescriptionTest {

	@Test
	void shouldCreateValidDescription() {
		Description description = new Description("Un iPhone en perfecto estado");
		assertEquals("Un iPhone en perfecto estado", description.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(DescriptionNullException.class, () -> new Description(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(DescriptionBlankException.class, () -> new Description(""));
		assertThrows(DescriptionBlankException.class, () -> new Description("   "));
	}

	@Test
	void shouldThrowWhenTooLong() {
		String longDescription = "A".repeat(2001);
		assertThrows(DescriptionTooLongException.class, () -> new Description(longDescription));
	}

	@Test
	void shouldAcceptMaxLength() {
		String maxDescription = "A".repeat(2000);
		Description description = new Description(maxDescription);
		assertEquals(2000, description.getValue().length());
	}
}

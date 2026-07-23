package segundum.domain.models.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.category.categoryid.CategoryIdBlankException;
import segundum.domain.exceptions.category.categoryid.CategoryIdInvalidFormatException;
import segundum.domain.exceptions.category.categoryid.CategoryIdNullException;
import segundum.domain.exceptions.category.categoryid.CategoryIdNonPositiveException;

class CategoryIdTest {

	@Test
	void shouldCreateValidCategoryId() {
		CategoryId id = CategoryId.fromString("123");
		assertEquals("123", id.getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(CategoryIdNullException.class, () -> CategoryId.fromString(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(CategoryIdBlankException.class, () -> CategoryId.fromString(""));
		assertThrows(CategoryIdBlankException.class, () -> CategoryId.fromString("   "));
	}

	@Test
	void shouldThrowWhenNotNumeric() {
		assertThrows(CategoryIdInvalidFormatException.class, () -> CategoryId.fromString("abc"));
		assertThrows(CategoryIdInvalidFormatException.class, () -> CategoryId.fromString("12a3"));
	}

	@Test
	void shouldThrowWhenNonPositive() {
		assertThrows(CategoryIdNonPositiveException.class, () -> CategoryId.fromString("0"));
		assertThrows(CategoryIdNonPositiveException.class, () -> CategoryId.fromString("-1"));
	}
}

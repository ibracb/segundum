package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.productid.ProductIdBlankException;
import segundum.domain.exceptions.sale.productid.ProductIdInvalidFormatException;
import segundum.domain.exceptions.sale.productid.ProductIdNullException;

class ProductIdTest {

	@Test
	void shouldCreateFromString() {
		UUID uuid = UUID.randomUUID();
		assertEquals(uuid, ProductId.fromString(uuid.toString()).getValue());
	}

	@Test
	void shouldCreateFromUUID() {
		UUID uuid = UUID.randomUUID();
		assertEquals(uuid, ProductId.fromUUID(uuid).getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(ProductIdNullException.class, () -> ProductId.fromString(null));
		assertThrows(ProductIdNullException.class, () -> ProductId.fromUUID(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(ProductIdBlankException.class, () -> ProductId.fromString(""));
		assertThrows(ProductIdBlankException.class, () -> ProductId.fromString("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(ProductIdInvalidFormatException.class, () -> ProductId.fromString("not-a-uuid"));
	}

}

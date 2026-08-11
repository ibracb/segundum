package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.sellerid.SellerIdBlankException;
import segundum.domain.exceptions.sale.sellerid.SellerIdInvalidFormatException;
import segundum.domain.exceptions.sale.sellerid.SellerIdNullException;

class SellerIdTest {

	@Test
	void shouldCreateFromString() {
		UUID uuid = UUID.randomUUID();
		assertEquals(uuid, SellerId.fromString(uuid.toString()).getValue());
	}

	@Test
	void shouldCreateFromUUID() {
		UUID uuid = UUID.randomUUID();
		assertEquals(uuid, SellerId.fromUUID(uuid).getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(SellerIdNullException.class, () -> SellerId.fromString(null));
		assertThrows(SellerIdNullException.class, () -> SellerId.fromUUID(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(SellerIdBlankException.class, () -> SellerId.fromString(""));
		assertThrows(SellerIdBlankException.class, () -> SellerId.fromString("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(SellerIdInvalidFormatException.class, () -> SellerId.fromString("not-a-uuid"));
	}

}

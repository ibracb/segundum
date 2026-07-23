package segundum.domain.models.seller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.seller.sellerid.SellerIdBlankException;
import segundum.domain.exceptions.seller.sellerid.SellerIdInvalidFormatException;
import segundum.domain.exceptions.seller.sellerid.SellerIdNullException;

class SellerIdTest {

	@Test
	void shouldCreateFromUUID() {
		UUID uuid = UUID.randomUUID();
		SellerId id = SellerId.fromUUID(uuid);
		assertEquals(uuid, id.getValue());
	}

	@Test
	void shouldCreateFromString() {
		String uuidStr = "550e8400-e29b-41d4-a716-446655440000";
		SellerId id = SellerId.fromString(uuidStr);
		assertEquals(UUID.fromString(uuidStr), id.getValue());
	}

	@Test
	void shouldThrowWhenNullFromUUID() {
		assertThrows(SellerIdNullException.class, () -> SellerId.fromUUID(null));
	}

	@Test
	void shouldThrowWhenNullFromString() {
		assertThrows(SellerIdNullException.class, () -> SellerId.fromString(null));
	}

	@Test
	void shouldThrowWhenBlankFromString() {
		assertThrows(SellerIdBlankException.class, () -> SellerId.fromString(""));
		assertThrows(SellerIdBlankException.class, () -> SellerId.fromString("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(SellerIdInvalidFormatException.class, () -> SellerId.fromString("not-a-uuid"));
	}
}

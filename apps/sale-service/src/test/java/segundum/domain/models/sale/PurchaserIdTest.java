package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.sale.purchaserid.PurchaserIdBlankException;
import segundum.domain.exceptions.sale.purchaserid.PurchaserIdInvalidFormatException;
import segundum.domain.exceptions.sale.purchaserid.PurchaserIdNullException;

class PurchaserIdTest {

	@Test
	void shouldCreateFromString() {
		UUID uuid = UUID.randomUUID();
		assertEquals(uuid, PurchaserId.fromString(uuid.toString()).getValue());
	}

	@Test
	void shouldCreateFromUUID() {
		UUID uuid = UUID.randomUUID();
		assertEquals(uuid, PurchaserId.fromUUID(uuid).getValue());
	}

	@Test
	void shouldThrowWhenNull() {
		assertThrows(PurchaserIdNullException.class, () -> PurchaserId.fromString(null));
		assertThrows(PurchaserIdNullException.class, () -> PurchaserId.fromUUID(null));
	}

	@Test
	void shouldThrowWhenBlank() {
		assertThrows(PurchaserIdBlankException.class, () -> PurchaserId.fromString(""));
		assertThrows(PurchaserIdBlankException.class, () -> PurchaserId.fromString("   "));
	}

	@Test
	void shouldThrowWhenInvalidFormat() {
		assertThrows(PurchaserIdInvalidFormatException.class, () -> PurchaserId.fromString("not-a-uuid"));
	}

}

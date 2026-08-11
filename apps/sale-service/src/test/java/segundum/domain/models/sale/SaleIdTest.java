package segundum.domain.models.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class SaleIdTest {

	@Test
	void shouldGenerateId() {
		SaleId a = SaleId.generate();
		assertNotNull(a);
		assertNotNull(a.getValue());
		assertEquals(a.asString(), a.getValue().toString());
	}

	@Test
	void shouldCreateFromString() {
		UUID uuid = UUID.randomUUID();
		SaleId saleId = SaleId.fromString(uuid.toString());
		assertEquals(uuid, saleId.getValue());
		assertEquals(uuid.toString(), saleId.asString());
	}

	@Test
	void shouldThrowWhenInvalidUuid() {
		assertThrows(IllegalArgumentException.class, () -> SaleId.fromString("not-a-uuid"));
	}

	@Test
	void shouldBeEqualOnSameValue() {
		UUID uuid = UUID.randomUUID();
		SaleId a = SaleId.fromString(uuid.toString());
		SaleId b = SaleId.fromString(uuid.toString());
		assertEquals(a, b);
		assertEquals(a.hashCode(), b.hashCode());
	}

}

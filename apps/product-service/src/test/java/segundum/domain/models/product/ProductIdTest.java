package segundum.domain.models.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class ProductIdTest {

	@Test
	void shouldGenerateUniqueIds() {
		ProductId id1 = ProductId.generate();
		ProductId id2 = ProductId.generate();
		assertNotNull(id1.getValue());
		assertNotEquals(id1.getValue(), id2.getValue());
	}

	@Test
	void shouldCreateFromUUID() {
		UUID uuid = UUID.randomUUID();
		ProductId id = ProductId.fromUUID(uuid);
		assertEquals(uuid, id.getValue());
	}

	@Test
	void shouldCreateFromString() {
		String uuidStr = "550e8400-e29b-41d4-a716-446655440000";
		ProductId id = ProductId.fromString(uuidStr);
		assertEquals(UUID.fromString(uuidStr), id.getValue());
	}
}

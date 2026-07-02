package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserIdTest {

	@Test
	void shouldGenerateUniqueIds() {
		UserId first = UserId.generate();
		UserId second = UserId.generate();
		assertNotEquals(first.getValue(), second.getValue());
	}

	@Test
	void shouldCreateFromValidString() {
		String uuid = "550e8400-e29b-41d4-a716-446655440000";
		UserId userId = UserId.fromString(uuid);
		assertEquals(uuid, userId.getValue().toString());
	}

	@Test
	void shouldThrowWhenInvalidString() {
		assertThrows(IllegalArgumentException.class, () -> UserId.fromString("invalid-uuid"));
	}

}

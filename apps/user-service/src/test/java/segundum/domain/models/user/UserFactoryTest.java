package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class UserFactoryTest {

	private final Name name = new Name("Juan");
	private final Surname surname = new Surname("Pérez");
	private final Email email = new Email("juan@email.com");
	private final Password password = Password.plain("Abcdef123");
	private final Birthdate birthdate = new Birthdate(LocalDate.of(1990, 5, 15));
	private final Phone phone = new Phone("+34612345678");

	@Test
	void shouldCreateUser() {
		User user = UserFactory.create(name, surname, email, password, birthdate, phone);

		assertNotNull(user.getUserId());
		assertEquals("Juan", user.getName().getValue());
		assertEquals("Pérez", user.getSurname().getValue());
		assertEquals("juan@email.com", user.getEmail().getValue());
		assertEquals("Abcdef123", user.getPassword().getValue());
		assertEquals(LocalDate.of(1990, 5, 15), user.getBirthdate().getValue());
		assertEquals("+34612345678", user.getPhone().getValue());
		assertEquals(0, user.getPurchases());
		assertEquals(0, user.getSales());
	}

	@Test
	void shouldReconstituteUser() {
		UserId userId = UserId.fromString("550e8400-e29b-41d4-a716-446655440000");
		User user = UserFactory.reconstitute(userId, name, surname, email, password, birthdate, phone, 5, 3, UserStatus.ACTIVE);

		assertEquals(userId.getValue(), user.getUserId().getValue());
		assertEquals("Juan", user.getName().getValue());
		assertEquals(5, user.getPurchases());
		assertEquals(3, user.getSales());
	}

}

package segundum.domain.models.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.domain.exceptions.SameValueException;
import segundum.domain.exceptions.user.status.UserNotActiveException;

class UserTest {

	private Name name;
	private Surname surname;
	private Email email;
	private Password password;
	private Birthdate birthdate;
	private Phone phone;
	private User user;

	@BeforeEach
	void setUp() {
		name = new Name("Juan");
		surname = new Surname("Pérez");
		email = new Email("juan@email.com");
		password = Password.plain("Abcdef123");
		birthdate = new Birthdate(LocalDate.of(1990, 5, 15));
		phone = new Phone("+34612345678");
		user = UserFactory.create(name, surname, email, password, birthdate, phone);
	}

	@Test
	void shouldChangeName() {
		Name newName = new Name("Carlos");
		user.changeName(newName);
		assertEquals("Carlos", user.getName().getValue());
	}

	@Test
	void shouldThrowWhenSameName() {
		assertThrows(SameValueException.class, () -> user.changeName(new Name("Juan")));
	}

	@Test
	void shouldChangeSurname() {
		Surname newSurname = new Surname("García");
		user.changeSurname(newSurname);
		assertEquals("García", user.getSurname().getValue());
	}

	@Test
	void shouldThrowWhenSameSurname() {
		assertThrows(SameValueException.class, () -> user.changeSurname(new Surname("Pérez")));
	}

	@Test
	void shouldChangePassword() {
		Password newPassword = Password.plain("NewPass123");
		user.changePassword(newPassword);
		assertEquals("NewPass123", user.getPassword().getValue());
	}

	@Test
	void shouldThrowWhenSamePassword() {
		assertThrows(SameValueException.class, () -> user.changePassword(password));
	}

	@Test
	void shouldChangePhone() {
		Phone newPhone = new Phone("+34698765432");
		user.changePhone(newPhone);
		assertEquals("+34698765432", user.getPhone().getValue());
	}

	@Test
	void shouldThrowWhenSamePhone() {
		assertThrows(SameValueException.class, () -> user.changePhone(phone));
	}

	@Test
	void shouldIncrementPurchases() {
		user.incrementPurchases();
		assertEquals(1, user.getPurchases());
		user.incrementPurchases();
		assertEquals(2, user.getPurchases());
	}

	@Test
	void shouldIncrementSales() {
		user.incrementSales();
		assertEquals(1, user.getSales());
		user.incrementSales();
		assertEquals(2, user.getSales());
	}

	@Test
	void shouldReturnCorrectGetters() {
		assertNotNull(user.getUserId());
		assertEquals("Juan", user.getName().getValue());
		assertEquals("Pérez", user.getSurname().getValue());
		assertEquals("juan@email.com", user.getEmail().getValue());
		assertEquals("Abcdef123", user.getPassword().getValue());
		assertEquals(LocalDate.of(1990, 5, 15), user.getBirthdate().getValue());
		assertEquals("+34612345678", user.getPhone().getValue());
		assertEquals(0, user.getPurchases());
		assertEquals(0, user.getSales());
		assertNotNull(user.getRegistrationDate());
	}

	@Test
	void shouldReturnActiveStatusByDefault() {
		assertEquals(UserStatus.ACTIVE, user.getStatus());
		assertTrue(user.isActive());
	}

	@Test
	void shouldDeactivateUser() {
		user.deactivate();
		assertEquals(UserStatus.INACTIVE, user.getStatus());
		assertFalse(user.isActive());
	}

	@Test
	void shouldThrowWhenChangingNameIfInactive() {
		user.deactivate();
		assertThrows(UserNotActiveException.class, () -> user.changeName(new Name("Carlos")));
	}

	@Test
	void shouldThrowWhenChangingSurnameIfInactive() {
		user.deactivate();
		assertThrows(UserNotActiveException.class, () -> user.changeSurname(new Surname("García")));
	}

	@Test
	void shouldReturnCorrectRoles() {
		assertNotNull(user.getUserRoles());
		assertEquals(1, user.getUserRoles().size());
		assertTrue(user.hasRole(UserRole.USER));
	}

	@Test
	void shouldReturnFalseForNonAssignedRole() {
		assertFalse(user.hasRole(UserRole.ADMINISTRATOR));
	}

}

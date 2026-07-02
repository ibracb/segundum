package segundum.application.interactors;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RegisterUserCommand;
import segundum.domain.exceptions.email.EmailAlreadyExistsException;
import segundum.domain.exceptions.phone.PhoneAlreadyExistsException;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class RegisterUserInteractorTest {

	private FakeUserRepository repository;
	private RegisterUserInteractor interactor;

	private final Name name = new Name("Juan");
	private final Surname surname = new Surname("Pérez");
	private final Email email = new Email("juan@email.com");
	private final Password password = new Password("Abcdef123");
	private final Birthdate birthdate = new Birthdate(LocalDate.of(1990, 5, 15));
	private final Phone phone = new Phone("+34612345678");

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		interactor = new RegisterUserInteractor(repository);
	}

	@Test
	void shouldRegisterUser() {
		RegisterUserCommand command = new RegisterUserCommand(name, surname, email, password, birthdate, phone);
		User user = interactor.execute(command);

		assertNotNull(user.getUserId());
		assertEquals("Juan", user.getName().getValue());
		assertEquals("Pérez", user.getSurname().getValue());
		assertEquals("juan@email.com", user.getEmail().getValue());
		assertEquals("+34612345678", user.getPhone().getValue());
		assertEquals(LocalDate.of(1990, 5, 15), user.getBirthdate().getValue());
		assertEquals(0, user.getPurchases());
		assertEquals(0, user.getSales());
		assertTrue(repository.findById(user.getUserId()).isPresent());
	}

	@Test
	void shouldThrowWhenEmailAlreadyExists() {
		RegisterUserCommand firstCommand = new RegisterUserCommand(name, surname, email, password, birthdate, phone);
		interactor.execute(firstCommand);

		RegisterUserCommand secondCommand = new RegisterUserCommand(
				new Name("Ana"), new Surname("García"), email,
				new Password("NewPass456"), birthdate, new Phone("+34698765432"));

		assertThrows(EmailAlreadyExistsException.class, () -> interactor.execute(secondCommand));
	}

	@Test
	void shouldThrowWhenPhoneAlreadyExists() {
		RegisterUserCommand firstCommand = new RegisterUserCommand(name, surname, email, password, birthdate, phone);
		interactor.execute(firstCommand);

		RegisterUserCommand secondCommand = new RegisterUserCommand(
				new Name("Ana"), new Surname("García"), new Email("ana@email.com"),
				new Password("NewPass456"), birthdate, phone);

		assertThrows(PhoneAlreadyExistsException.class, () -> interactor.execute(secondCommand));
	}

}

package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.domain.events.UserRegistered;
import segundum.domain.exceptions.user.email.EmailAlreadyExistsException;
import segundum.domain.exceptions.user.phone.PhoneAlreadyExistsException;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.outbound.PasswordHasher;
import segundum.domain.repositories.UserRepository;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakePasswordHasher;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class RegisterUserInteractorTest {

	private UserRepository repository;
	private FakePublisher publisher;
	private PasswordHasher passwordHasher;
	private RegisterUserUseCase interactor;

	private final Name name = new Name("Juan");
	private final Surname surname = new Surname("Pérez");
	private final Email email = new Email("juan@email.com");
	private final Password password = Password.plain("Abcdef123");
	private final Birthdate birthdate = new Birthdate(LocalDate.of(1990, 5, 15));
	private final Phone phone = new Phone("+34612345678");

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		publisher = new FakePublisher();
		passwordHasher = new FakePasswordHasher();
		interactor = new RegisterUserInteractor(repository, publisher, passwordHasher);
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
	void shouldPublishUserRegisteredEvent() {
		RegisterUserCommand command = new RegisterUserCommand(name, surname, email, password, birthdate, phone);
		User user = interactor.execute(command);

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof UserRegistered);
		UserRegistered event = (UserRegistered) publisher.getPublishedEvents().get(0);
		assertEquals(user.getUserId(), event.getUserId());
		assertEquals("Juan", event.getName().getValue());
		assertEquals("Pérez", event.getSurname().getValue());
		assertEquals("juan@email.com", event.getEmail().getValue());
	}

	@Test
	void shouldNotPublishEventWhenEmailAlreadyExists() {
		RegisterUserCommand firstCommand = new RegisterUserCommand(name, surname, email, password, birthdate, phone);
		interactor.execute(firstCommand);

		RegisterUserCommand secondCommand = new RegisterUserCommand(
				new Name("Ana"), new Surname("García"), email,
				Password.plain("NewPass456"), birthdate, new Phone("+34698765432"));

		assertThrows(EmailAlreadyExistsException.class, () -> interactor.execute(secondCommand));
		assertEquals(1, publisher.getPublishedEvents().size());
	}

	@Test
	void shouldNotPublishEventWhenPhoneAlreadyExists() {
		RegisterUserCommand firstCommand = new RegisterUserCommand(name, surname, email, password, birthdate, phone);
		interactor.execute(firstCommand);

		RegisterUserCommand secondCommand = new RegisterUserCommand(
				new Name("Ana"), new Surname("García"), new Email("ana@email.com"),
				Password.plain("NewPass456"), birthdate, phone);

		assertThrows(PhoneAlreadyExistsException.class, () -> interactor.execute(secondCommand));
		assertEquals(1, publisher.getPublishedEvents().size());
	}

}

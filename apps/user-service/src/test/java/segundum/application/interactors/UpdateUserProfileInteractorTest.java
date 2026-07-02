package segundum.application.interactors;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.commands.UpdateUserCommand;
import segundum.domain.events.UserUpdated;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class UpdateUserProfileInteractorTest {

	private FakeUserRepository repository;
	private FakePublisher publisher;
	private UpdateUserProfileInteractor interactor;
	private User existingUser;

	private final Name name = new Name("Juan");
	private final Surname surname = new Surname("Pérez");
	private final Email email = new Email("juan@email.com");
	private final Password password = new Password("Abcdef123");
	private final Birthdate birthdate = new Birthdate(LocalDate.of(1990, 5, 15));
	private final Phone phone = new Phone("+34612345678");

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		publisher = new FakePublisher();
		interactor = new UpdateUserProfileInteractor(repository, publisher);

		RegisterUserCommand registerCommand = new RegisterUserCommand(
				name, surname, email, password, birthdate, phone);
		RegisterUserInteractor registerInteractor = new RegisterUserInteractor(repository, publisher);
		existingUser = registerInteractor.execute(registerCommand);
		publisher.clear();
	}

	@Test
	void shouldUpdateAllFields() {
		Name newName = new Name("Carlos");
		Surname newSurname = new Surname("García");
		Password newPassword = new Password("NewPass456");
		Phone newPhone = new Phone("+34698765432");

		UpdateUserCommand command = new UpdateUserCommand(
				existingUser.getUserId(), newName, newSurname, newPassword, newPhone);
		User updatedUser = interactor.execute(command);

		assertEquals("Carlos", updatedUser.getName().getValue());
		assertEquals("García", updatedUser.getSurname().getValue());
		assertEquals("NewPass456", updatedUser.getPassword().getValue());
		assertEquals("+34698765432", updatedUser.getPhone().getValue());
		assertEquals("juan@email.com", updatedUser.getEmail().getValue());
	}

	@Test
	void shouldPublishUserUpdatedEvent() {
		Name newName = new Name("Carlos");
		Surname newSurname = new Surname("García");

		UpdateUserCommand command = new UpdateUserCommand(
				existingUser.getUserId(), newName, newSurname, null, null);
		interactor.execute(command);

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof UserUpdated);
		UserUpdated event = (UserUpdated) publisher.getPublishedEvents().get(0);
		assertEquals(existingUser.getUserId(), event.getUserId());
		assertEquals("Carlos", event.getName().getValue());
		assertEquals("García", event.getSurname().getValue());
	}

	@Test
	void shouldUpdateOnlyNameAndPhone() {
		Name newName = new Name("Carlos");
		Phone newPhone = new Phone("+34698765432");

		UpdateUserCommand command = new UpdateUserCommand(
				existingUser.getUserId(), newName, null, null, newPhone);
		User updatedUser = interactor.execute(command);

		assertEquals("Carlos", updatedUser.getName().getValue());
		assertEquals("Pérez", updatedUser.getSurname().getValue());
		assertEquals("Abcdef123", updatedUser.getPassword().getValue());
		assertEquals("+34698765432", updatedUser.getPhone().getValue());
	}

	@Test
	void shouldThrowWhenUserNotFound() {
		UserId nonExistentId = UserId.generate();
		UpdateUserCommand command = new UpdateUserCommand(
				nonExistentId, name, surname, password, phone);

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(command));
	}

}

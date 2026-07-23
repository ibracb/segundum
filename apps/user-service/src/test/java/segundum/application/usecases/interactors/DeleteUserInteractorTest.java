package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.DeleteUserCommand;
import segundum.application.usecases.DeleteUserUseCase;
import segundum.application.commands.RegisterUserCommand;
import segundum.domain.events.UserDeleted;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.repositories.UserRepository;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakePasswordHasher;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class DeleteUserInteractorTest {

	private UserRepository repository;
	private FakePublisher publisher;
	private DeleteUserUseCase interactor;
	private User existingUser;

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		publisher = new FakePublisher();
		interactor = new DeleteUserInteractor(repository, publisher);

		RegisterUserCommand registerCommand = new RegisterUserCommand(
				new Name("Juan"), new Surname("Pérez"),
				new Email("juan@email.com"), Password.plain("Abcdef123"),
				new Birthdate(LocalDate.of(1990, 5, 15)),
				new Phone("+34612345678"));
		RegisterUserInteractor registerInteractor = new RegisterUserInteractor(repository, publisher, new FakePasswordHasher());
		existingUser = registerInteractor.execute(registerCommand);
		publisher.clear();
	}

	@Test
	void shouldDeleteUser() {
		UserId userId = existingUser.getUserId();
		interactor.execute(new DeleteUserCommand(userId));
		assertTrue(repository.findById(userId).isPresent());
		assertTrue(repository.findById(userId).get().isDeleted());
	}

	@Test
	void shouldPublishUserDeletedEvent() {
		UserId userId = existingUser.getUserId();
		interactor.execute(new DeleteUserCommand(userId));

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof UserDeleted);
		UserDeleted event = (UserDeleted) publisher.getPublishedEvents().get(0);
		assertEquals(userId, event.getUserId());
	}

	@Test
	void shouldThrowWhenUserNotFound() {
		UserId nonExistentId = UserId.generate();
		assertThrows(EntityNotFoundException.class, () -> interactor.execute(new DeleteUserCommand(nonExistentId)));
	}

}

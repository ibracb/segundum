package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.DeactivateUserCommand;
import segundum.application.usecases.DeactivateUserUseCase;
import segundum.application.commands.RegisterUserCommand;
import segundum.domain.events.UserDeactivated;
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

class DeactivateUserInteractorTest {

	private UserRepository repository;
	private FakePublisher publisher;
	private DeactivateUserUseCase interactor;
	private User existingUser;

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		publisher = new FakePublisher();
		interactor = new DeactivateUserInteractor(repository, publisher);

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
	void shouldDeactivateUser() {
		UserId userId = existingUser.getUserId();
		interactor.execute(new DeactivateUserCommand(userId));
		assertTrue(repository.findById(userId).isPresent());
		assertFalse(repository.findById(userId).get().isActive());
	}

	@Test
	void shouldPublishUserDeactivatedEvent() {
		UserId userId = existingUser.getUserId();
		interactor.execute(new DeactivateUserCommand(userId));

		assertEquals(1, publisher.getPublishedEvents().size());
		assertTrue(publisher.getPublishedEvents().get(0) instanceof UserDeactivated);
		UserDeactivated event = (UserDeactivated) publisher.getPublishedEvents().get(0);
		assertEquals(userId, event.getUserId());
	}

	@Test
	void shouldThrowWhenUserNotFound() {
		UserId nonExistentId = UserId.generate();
		assertThrows(EntityNotFoundException.class, () -> interactor.execute(new DeactivateUserCommand(nonExistentId)));
	}

}

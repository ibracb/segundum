package segundum.application.interactors;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RegisterUserCommand;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class DeleteUserInteractorTest {

	private FakeUserRepository repository;
	private DeleteUserInteractor interactor;
	private User existingUser;

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		interactor = new DeleteUserInteractor(repository);

		RegisterUserCommand registerCommand = new RegisterUserCommand(
				new Name("Juan"), new Surname("Pérez"),
				new Email("juan@email.com"), new Password("Abcdef123"),
				new Birthdate(LocalDate.of(1990, 5, 15)),
				new Phone("+34612345678"));
		RegisterUserInteractor registerInteractor = new RegisterUserInteractor(repository);
		existingUser = registerInteractor.execute(registerCommand);
	}

	@Test
	void shouldDeleteUser() {
		UserId userId = existingUser.getUserId();
		interactor.execute(userId);
		assertFalse(repository.findById(userId).isPresent());
	}

	@Test
	void shouldNotThrowWhenUserNotFound() {
		UserId nonExistentId = UserId.generate();
		assertDoesNotThrow(() -> interactor.execute(nonExistentId));
	}

}

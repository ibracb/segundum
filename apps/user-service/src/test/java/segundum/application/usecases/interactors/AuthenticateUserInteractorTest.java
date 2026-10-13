package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.AuthenticateUserCommand;
import segundum.application.commands.RegisterUserCommand;
import segundum.application.dtos.auth.AuthenticatedUserData;
import segundum.application.usecases.AuthenticateUserUseCase;
import segundum.domain.exceptions.user.authentication.BadCredentialsException;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.models.user.UserRole;
import segundum.domain.repositories.UserRepository;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakePasswordHasher;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class AuthenticateUserInteractorTest {

	private static final String PASSWORD = "Abcdef123";

	private UserRepository repository;
	private AuthenticateUserUseCase interactor;
	private User existingUser;

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		interactor = new AuthenticateUserInteractor(repository, new FakePasswordHasher());

		RegisterUserCommand registerCommand = new RegisterUserCommand(
				new Name("Juan"), new Surname("Pérez"),
				new Email("juan@email.com"), Password.plain(PASSWORD),
				new Birthdate(LocalDate.of(1990, 5, 15)),
				new Phone("+34612345678"));
		RegisterUserInteractor registerInteractor = new RegisterUserInteractor(
				repository, new FakePublisher(), new FakePasswordHasher());
		UserId userId = registerInteractor.execute(registerCommand);
		existingUser = repository.findById(userId).get();
	}

	@Test
	void shouldReturnUserDataWithValidCredentials() {
		AuthenticatedUserData result = interactor.execute(
				new AuthenticateUserCommand(new Email("juan@email.com"), PASSWORD));

		List<String> expectedRoles = existingUser.getUserRoles().stream()
				.map(UserRole::name)
				.collect(Collectors.toList());
		assertEquals(existingUser.getUserId().getValue().toString(), result.getId());
		assertEquals("Juan Pérez", result.getFullName());
		assertEquals(expectedRoles, result.getRoles());
	}

	@Test
	void shouldThrowBadCredentialsWhenUserDoesNotExist() {
		assertThrows(BadCredentialsException.class,
				() -> interactor.execute(new AuthenticateUserCommand(new Email("otro@email.com"), PASSWORD)));
	}

	@Test
	void shouldThrowBadCredentialsWhenPasswordIsWrong() {
		assertThrows(BadCredentialsException.class,
				() -> interactor.execute(new AuthenticateUserCommand(new Email("juan@email.com"), "Incorrecta123")));
	}

	@Test
	void shouldThrowBadCredentialsWhenUserIsInactive() {
		existingUser.deactivate();
		repository.update(existingUser);

		assertThrows(BadCredentialsException.class,
				() -> interactor.execute(new AuthenticateUserCommand(new Email("juan@email.com"), PASSWORD)));
	}

}

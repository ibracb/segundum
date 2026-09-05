package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.queries.GetUserStatsQuery;
import segundum.application.readmodels.user.UserStatsReadModel;
import segundum.application.usecases.GetUserStatsUseCase;
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
import segundum.infrastructure.persistence.fakes.finders.FakeUserFinder;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class GetUserStatsInteractorTest {

	private UserRepository repository;
	private GetUserStatsUseCase interactor;
	private User existingUser;

	private final Name name = new Name("Juan");
	private final Surname surname = new Surname("Pérez");
	private final Email email = new Email("juan@email.com");
	private final Password password = Password.plain("Abcdef123");
	private final Birthdate birthdate = new Birthdate(LocalDate.of(1990, 5, 15));
	private final Phone phone = new Phone("+34612345678");

	@BeforeEach
	void setUp() {
		repository = new FakeUserRepository();
		FakeUserFinder userFinder = new FakeUserFinder((FakeUserRepository) repository);
		interactor = new GetUserStatsInteractor(userFinder);

		RegisterUserCommand registerCommand = new RegisterUserCommand(
				name, surname, email, password, birthdate, phone);
		RegisterUserInteractor registerInteractor = new RegisterUserInteractor(repository, new FakePublisher(), new FakePasswordHasher());
		existingUser = registerInteractor.execute(registerCommand);
	}

	@Test
	void shouldReturnUserStatsWhenUserExists() {
		UserStatsReadModel stats = interactor.execute(new GetUserStatsQuery(existingUser.getUserId()));

		assertEquals(existingUser.getUserId().getValue().toString(), stats.getId());
		assertEquals(0, stats.getPurchases());
		assertEquals(0, stats.getSales());
	}

	@Test
	void shouldThrowWhenUserNotFound() {
		UserId nonExistentId = UserId.generate();

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(new GetUserStatsQuery(nonExistentId)));
	}

}

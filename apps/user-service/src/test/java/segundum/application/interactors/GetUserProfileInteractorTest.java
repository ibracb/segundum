package segundum.application.interactors;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.queries.GetUserProfileQuery;
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

class GetUserProfileInteractorTest {

	private FakeUserRepository repository;
	private GetUserProfileInteractor interactor;
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
		interactor = new GetUserProfileInteractor(repository);

		RegisterUserCommand registerCommand = new RegisterUserCommand(
				name, surname, email, password, birthdate, phone);
		RegisterUserInteractor registerInteractor = new RegisterUserInteractor(repository, new FakePublisher());
		existingUser = registerInteractor.execute(registerCommand);
	}

	@Test
	void shouldReturnUserProfileWhenUserExists() {
		User user = interactor.execute(new GetUserProfileQuery(existingUser.getUserId()));

		assertEquals(existingUser.getUserId(), user.getUserId());
		assertEquals("Juan", user.getName().getValue());
		assertEquals("Pérez", user.getSurname().getValue());
		assertEquals("juan@email.com", user.getEmail().getValue());
		assertEquals("+34612345678", user.getPhone().getValue());
		assertEquals(LocalDate.of(1990, 5, 15), user.getBirthdate().getValue());
		assertEquals(0, user.getPurchases());
		assertEquals(0, user.getSales());
	}

	@Test
	void shouldThrowWhenUserNotFound() {
		UserId nonExistentId = UserId.generate();

		assertThrows(EntityNotFoundException.class, () -> interactor.execute(new GetUserProfileQuery(nonExistentId)));
	}

}

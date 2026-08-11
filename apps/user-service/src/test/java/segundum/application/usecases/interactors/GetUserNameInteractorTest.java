package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.queries.GetUserNameQuery;
import segundum.application.usecases.GetUserNameUseCase;
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

class GetUserNameInteractorTest {

    private UserRepository repository;
    private GetUserNameUseCase interactor;
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
        interactor = new GetUserNameInteractor(repository);

        RegisterUserCommand registerCommand = new RegisterUserCommand(
                name, surname, email, password, birthdate, phone);
        RegisterUserInteractor registerInteractor = new RegisterUserInteractor(repository, new FakePublisher(), new FakePasswordHasher());
        existingUser = registerInteractor.execute(registerCommand);
    }

    @Test
    void shouldReturnUserNameWhenUserExists() {
        User user = interactor.execute(new GetUserNameQuery(existingUser.getUserId()));

        assertEquals(existingUser.getUserId(), user.getUserId());
        assertEquals("Juan", user.getName().getValue());
        assertEquals("Pérez", user.getSurname().getValue());
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        UserId nonExistentId = UserId.generate();

        assertThrows(EntityNotFoundException.class, () -> interactor.execute(new GetUserNameQuery(nonExistentId)));
    }

}

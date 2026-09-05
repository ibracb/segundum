package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.queries.GetUserListQuery;
import segundum.application.readmodels.user.UserInfoReadModel;
import segundum.application.usecases.GetUserListUseCase;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.repositories.UserRepository;
import segundum.infrastructure.messaging.fakes.publishers.FakePublisher;
import segundum.infrastructure.persistence.fakes.FakePasswordHasher;
import segundum.infrastructure.persistence.fakes.finders.FakeUserFinder;
import segundum.infrastructure.persistence.fakes.repositories.FakeUserRepository;

class GetUserListInteractorTest {

    private UserRepository repository;
    private GetUserListUseCase interactor;

    private final Name name1 = new Name("Juan");
    private final Surname surname1 = new Surname("Pérez");
    private final Email email1 = new Email("juan@email.com");
    private final Password password1 = Password.plain("Abcdef123");
    private final Birthdate birthdate1 = new Birthdate(LocalDate.of(1990, 5, 15));
    private final Phone phone1 = new Phone("+34612345678");

    private final Name name2 = new Name("Ana");
    private final Surname surname2 = new Surname("García");
    private final Email email2 = new Email("ana@email.com");
    private final Password password2 = Password.plain("NewPass456");
    private final Birthdate birthdate2 = new Birthdate(LocalDate.of(1985, 10, 20));
    private final Phone phone2 = new Phone("+34698765432");

    @BeforeEach
    void setUp() {
        repository = new FakeUserRepository();
        FakeUserFinder userFinder = new FakeUserFinder((FakeUserRepository) repository);
        interactor = new GetUserListInteractor(userFinder);
    }

    private User registerUser(Name name, Surname surname, Email email, Password password, Birthdate birthdate, Phone phone) {
        RegisterUserInteractor registerInteractor = new RegisterUserInteractor(repository, new FakePublisher(), new FakePasswordHasher());
        return registerInteractor.execute(new RegisterUserCommand(name, surname, email, password, birthdate, phone));
    }

    @Test
    void shouldReturnEmptyListWhenNoUsers() {
        List<UserInfoReadModel> result = interactor.execute(new GetUserListQuery());
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAllRegisteredUsers() {
        registerUser(name1, surname1, email1, password1, birthdate1, phone1);
        registerUser(name2, surname2, email2, password2, birthdate2, phone2);

        List<UserInfoReadModel> result = interactor.execute(new GetUserListQuery());

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnUserInfoWithCorrectFields() {
        User user = registerUser(name1, surname1, email1, password1, birthdate1, phone1);

        UserInfoReadModel info = interactor.execute(new GetUserListQuery()).get(0);

        assertEquals(user.getUserId().getValue().toString(), info.getId());
        assertEquals("Juan", info.getName());
        assertEquals("Pérez", info.getSurname());
        assertEquals("juan@email.com", info.getEmail());
        assertEquals(LocalDate.of(1990, 5, 15), info.getBirthdate());
        assertEquals("+34612345678", info.getPhone());
        assertEquals("ACTIVE", info.getStatus());
        assertNotNull(info.getRegistrationDate());
        assertTrue(info.getRoles().contains("USER"));
    }

}

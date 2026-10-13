package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.LoginCommand;
import segundum.application.dtos.auth.AuthenticatedUserData;
import segundum.infrastructure.persistence.fakes.gateways.FakeAuthenticateUser;

class LoginInteractorTest {

    private FakeAuthenticateUser authenticateUser;
    private LoginInteractor interactor;

    @BeforeEach
    void setUp() {
        authenticateUser = new FakeAuthenticateUser();
        interactor = new LoginInteractor(authenticateUser);
    }

    @Test
    void shouldFetchTheCredentialsAndReturnTheUserData() {
        AuthenticatedUserData userData = new AuthenticatedUserData(
                "user-1", "Juan Pérez", List.of("USER"));
        authenticateUser.setResult(userData);

        AuthenticatedUserData result = interactor.execute(
                new LoginCommand("juan@email.com", "Abcdef123"));

        assertSame(userData, result);
        assertEquals("juan@email.com", authenticateUser.getFetchedEmail());
        assertEquals("Abcdef123", authenticateUser.getFetchedPassword());
    }

    @Test
    void shouldReturnNullWhenTheCredentialsAreRejected() {
        authenticateUser.setResult(null);

        AuthenticatedUserData result = interactor.execute(
                new LoginCommand("juan@email.com", "Mal12345"));

        assertNull(result);
        assertEquals("juan@email.com", authenticateUser.getFetchedEmail());
        assertEquals("Mal12345", authenticateUser.getFetchedPassword());
    }

}

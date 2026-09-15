package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;

import segundum.application.commands.LoginCommand;
import segundum.application.readmodels.auth.AuthenticatedUserReadModel;
import segundum.application.usecases.LoginUseCase;

/**
 * Transaction boundary for the login use case.
 */
@Component
public class AuthFacade {

    /**
     * The login use case.
     */
    private final LoginUseCase loginUseCase;

    /**
     * Constructs a new AuthFacade with the given use case.
     *
     * @param loginUseCase the login use case
     */
    public AuthFacade(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    /**
     * Executes the login.
     *
     * @param command the login command
     * @return the authenticated user read model
     */
    public AuthenticatedUserReadModel run(LoginCommand command) {
        return loginUseCase.execute(command);
    }

}

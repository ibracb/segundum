package segundum.application.usecases.interactors;

import segundum.application.commands.LoginCommand;
import segundum.application.gateways.AuthenticateUser;
import segundum.application.readmodels.auth.AuthenticatedUserReadModel;
import segundum.application.usecases.LoginUseCase;

/**
 * Interactor for the login use case.
 * Delegates authentication to the user service via the AuthenticateUser gateway.
 */
public class LoginInteractor implements LoginUseCase {

    /**
     * The gateway for authenticating users.
     */
    private final AuthenticateUser authenticateUser;

    /**
     * Constructs a new LoginInteractor with the given gateway.
     *
     * @param authenticateUser the gateway for authenticating users
     */
    public LoginInteractor(AuthenticateUser authenticateUser) {
        this.authenticateUser = authenticateUser;
    }

    @Override
    public AuthenticatedUserReadModel execute(LoginCommand command) {
        return authenticateUser.fetch(command.getEmail(), command.getPassword());
    }

}

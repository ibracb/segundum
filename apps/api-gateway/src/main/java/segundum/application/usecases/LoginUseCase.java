package segundum.application.usecases;

import segundum.application.commands.LoginCommand;
import segundum.application.dtos.auth.AuthenticatedUserData;

/**
 * Use case for logging in a user.
 */
public interface LoginUseCase {

    /**
     * Executes the login use case.
     *
     * @param command the login command containing email and password
     * @return the authenticated user data
     */
    AuthenticatedUserData execute(LoginCommand command);

}

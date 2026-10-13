package segundum.application.usecases;

import segundum.application.commands.AuthenticateUserCommand;
import segundum.application.dtos.auth.AuthenticatedUserData;

/**
 * Use case for authenticating a user.
 */
public interface AuthenticateUserUseCase {

    /**
     * Executes the authentication use case.
     *
     * @param command the authentication command containing email and password
     * @return the authenticated user data
     */
    AuthenticatedUserData execute(AuthenticateUserCommand command);

}

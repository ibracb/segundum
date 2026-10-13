package segundum.application.gateways;

import segundum.application.dtos.auth.AuthenticatedUserData;

/**
 * Output port for authenticating a user against the user service.
 */
public interface AuthenticateUser {

    /**
     * Authenticates a user with the given email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return the authenticated user data
     */
    AuthenticatedUserData fetch(String email, String password);

}

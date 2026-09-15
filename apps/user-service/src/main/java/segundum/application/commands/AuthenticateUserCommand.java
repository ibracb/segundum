package segundum.application.commands;

import segundum.domain.models.user.Email;

/**
 * Represents a command to authenticate a user.
 */
public class AuthenticateUserCommand {

    /**
     * The email of the user.
     */
    private final Email email;

    /**
     * The plain-text password of the user.
     */
    private final String password;

    /**
     * Constructs a new AuthenticateUserCommand with the given email and password.
     *
     * @param email    the email of the user
     * @param password the plain-text password of the user
     */
    public AuthenticateUserCommand(Email email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the plain-text password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

}

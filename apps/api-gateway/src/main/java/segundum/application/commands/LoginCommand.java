package segundum.application.commands;

/**
 * Represents a command to log in a user.
 */
public class LoginCommand {

    /**
     * The email of the user.
     */
    private final String email;

    /**
     * The password of the user.
     */
    private final String password;

    /**
     * Constructs a new LoginCommand with the given email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     */
    public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

}

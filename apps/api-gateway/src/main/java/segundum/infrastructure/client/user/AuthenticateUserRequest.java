package segundum.infrastructure.client.user;

/**
 * Request body for authenticating a user via the user service.
 */
public class AuthenticateUserRequest {

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * Constructs a new AuthenticateUserRequest with the given email and password.
     *
     * @param email    the email address
     * @param password the password
     */
    public AuthenticateUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

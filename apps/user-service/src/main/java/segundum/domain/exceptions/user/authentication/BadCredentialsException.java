package segundum.domain.exceptions.user.authentication;

/**
 * Exception thrown when the provided credentials are invalid.
 */
@SuppressWarnings("serial")
public class BadCredentialsException extends RuntimeException {

    /**
     * Constructs a new BadCredentialsException.
     */
    public BadCredentialsException() {
        super("Invalid email or password");
    }

}

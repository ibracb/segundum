package segundum.infrastructure.rest.auth.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a request to authenticate a user.
 */
@Schema(description = "Request to authenticate a user")
public class LoginRequest {

    /**
     * The email address of the user.
     */
    @NotNull
    @NotBlank
    @Schema(description = "User email address", example = "john.doe@example.com")
    private String email;

    /**
     * The password of the user.
     */
    @NotNull
    @NotBlank
    @Schema(description = "User password", example = "securePass123")
    private String password;

    /**
     * Default constructor required by JSON deserialization.
     */
    public LoginRequest() {
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

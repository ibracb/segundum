package segundum.infrastructure.rest.auth.responses;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the authentication response containing the JWT token and user data.
 */
@Schema(description = "Authentication response")
public class AuthResponse {

    /**
     * The JWT token.
     */
    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;

    /**
     * The unique identifier of the user.
     */
    @Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String id;

    /**
     * The full name of the user.
     */
    @Schema(description = "User full name", example = "John Doe")
    private String fullName;

    /**
     * The roles of the user.
     */
    @Schema(description = "User roles", example = "[\"USER\"]")
    private List<String> roles;

    /**
     * Default constructor required by JSON deserialization.
     */
    public AuthResponse() {
    }

    /**
     * Constructs a new AuthResponse with the given data.
     *
     * @param token    the JWT token
     * @param id       the user identifier
     * @param fullName the user full name
     * @param roles    the user roles
     */
    public AuthResponse(String token, String id, String fullName, List<String> roles) {
        this.token = token;
        this.id = id;
        this.fullName = fullName;
        this.roles = roles;
    }

    /**
     * Returns the JWT token.
     *
     * @return the JWT token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the JWT token.
     *
     * @param token the JWT token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Returns the user identifier.
     *
     * @return the user identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the user identifier.
     *
     * @param id the user identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the full name of the user.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the user.
     *
     * @param fullName the full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the roles of the user.
     *
     * @return the user roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets the roles of the user.
     *
     * @param roles the user roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}

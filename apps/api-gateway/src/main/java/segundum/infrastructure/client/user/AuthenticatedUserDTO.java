package segundum.infrastructure.client.user;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for the authenticated user data returned by the user service.
 */
@Schema(description = "Authenticated user data from user service")
public class AuthenticatedUserDTO {

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
    public AuthenticatedUserDTO() {
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

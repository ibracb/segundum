package segundum.application.readmodels.user;

import java.util.List;

/**
 * Represents the authenticated user data returned after successful authentication.
 */
public class AuthenticatedUserReadModel {

    /**
     * The unique identifier of the user.
     */
    private final String id;

    /**
     * The full name of the user (name + surname).
     */
    private final String fullName;

    /**
     * The roles of the user.
     */
    private final List<String> roles;

    /**
     * Constructs a new AuthenticatedUserReadModel with the given data.
     *
     * @param id       the user identifier
     * @param fullName the user full name
     * @param roles    the user roles
     */
    public AuthenticatedUserReadModel(String id, String fullName, List<String> roles) {
        this.id = id;
        this.fullName = fullName;
        this.roles = roles;
    }

    /**
     * Returns the identifier of the user.
     *
     * @return the user identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the full name of the user.
     *
     * @return the user full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Returns the roles of the user.
     *
     * @return the user roles
     */
    public List<String> getRoles() {
        return roles;
    }

}

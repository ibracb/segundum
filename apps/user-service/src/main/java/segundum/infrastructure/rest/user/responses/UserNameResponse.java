package segundum.infrastructure.rest.user.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the name and surname of a user.
 */
@Schema(description = "Name and surname of a user")
public class UserNameResponse {

    /**
     * The unique identifier of the user.
     */
    @Schema(description = "User ID", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String id;

    /**
     * The first name of the user.
     */
    @Schema(description = "User's first name", example = "John")
    private String name;

    /**
     * The last name of the user.
     */
    @Schema(description = "User's last name", example = "Doe")
    private String surname;

    /**
     * Constructs a new UserNameResponse with the given parameters.
     *
     * @param id      the unique identifier of the user
     * @param name    the first name of the user
     * @param surname the last name of the user
     */
    public UserNameResponse(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the unique identifier of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the first name of the user.
     *
     * @return the first name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the last name of the user.
     *
     * @return the last name of the user
     */
    public String getSurname() {
        return surname;
    }

}

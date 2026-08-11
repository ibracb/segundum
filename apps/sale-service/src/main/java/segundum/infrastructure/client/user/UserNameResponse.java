package segundum.infrastructure.client.user;

/**
 * Represents the name response of a user.
 */
public class UserNameResponse {

    /**
     * The identifier of the user.
     */
    private String id;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The surname of the user.
     */
    private String surname;

    /**
     * Returns the identifier of the user.
     *
     * @return the identifier of the user
     */
    public String getId() { return id; }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() { return name; }

    /**
     * Returns the surname of the user.
     *
     * @return the surname of the user
     */
    public String getSurname() { return surname; }

    /**
     * Sets the identifier of the user.
     *
     * @param id the identifier of the user
     */
    public void setId(String id) { this.id = id; }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the surname of the user.
     *
     * @param surname the surname of the user
     */
    public void setSurname(String surname) { this.surname = surname; }

}

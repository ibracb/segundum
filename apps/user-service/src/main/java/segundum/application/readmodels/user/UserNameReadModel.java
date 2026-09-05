package segundum.application.readmodels.user;

/**
 * Represents the name data of a user used by the read side.
 */
public class UserNameReadModel {

    /**
     * The identifier of the user.
     */
    private final String id;

    /**
     * The name of the user.
     */
    private final String name;

    /**
     * The surname of the user.
     */
    private final String surname;

    /**
     * Constructs a new UserNameReadModel with the given data.
     *
     * @param id      the user identifier
     * @param name    the user name
     * @param surname the user surname
     */
    public UserNameReadModel(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the identifier of the user.
     *
     * @return the user identifier
     */
    public String getId() { return id; }

    /**
     * Returns the name of the user.
     *
     * @return the user name
     */
    public String getName() { return name; }

    /**
     * Returns the surname of the user.
     *
     * @return the user surname
     */
    public String getSurname() { return surname; }

}

package segundum.application.readmodels.user;

/**
 * Represents the profile data of a user used by the read side.
 */
public class UserProfileReadModel {

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
     * The email of the user.
     */
    private final String email;

    /**
     * The phone number of the user.
     */
    private final String phone;

    /**
     * Constructs a new UserProfileReadModel with the given data.
     *
     * @param id      the user identifier
     * @param name    the user name
     * @param surname the user surname
     * @param email   the user email
     * @param phone   the user phone number
     */
    public UserProfileReadModel(String id, String name, String surname, String email, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
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

    /**
     * Returns the email of the user.
     *
     * @return the user email
     */
    public String getEmail() { return email; }

    /**
     * Returns the phone number of the user.
     *
     * @return the user phone number
     */
    public String getPhone() { return phone; }

}

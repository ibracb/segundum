package segundum.infrastructure.messaging.messages;

/**
 * Represents a message for a user registered domain event.
 */
public class UserRegisteredMessage extends DomainEventMessage {

    /**
     * The unique identifier of the registered user.
     */
    private final String userId;
    /**
     * The name of the registered user.
     */
    private final String name;
    /**
     * The surname of the registered user.
     */
    private final String surname;
    /**
     * The email of the registered user.
     */
    private final String email;

    /**
     * Constructs a new UserRegisteredMessage with the given event data.
     *
     * @param eventId   the unique identifier of the event
     * @param type      the type of the event
     * @param timestamp the timestamp of the event
     * @param userId    the unique identifier of the registered user
     * @param name      the name of the registered user
     * @param surname   the surname of the registered user
     * @param email     the email of the registered user
     */
    public UserRegisteredMessage(String eventId, String type, String timestamp,
            String userId, String name, String surname, String email) {
        super(eventId, type, timestamp);
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /**
     * Returns the unique identifier of the registered user.
     *
     * @return the unique identifier of the registered user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the name of the registered user.
     *
     * @return the name of the registered user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the surname of the registered user.
     *
     * @return the surname of the registered user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the email of the registered user.
     *
     * @return the email of the registered user
     */
    public String getEmail() {
        return email;
    }

}

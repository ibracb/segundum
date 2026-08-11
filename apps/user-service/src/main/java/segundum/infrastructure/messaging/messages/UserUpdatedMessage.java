package segundum.infrastructure.messaging.messages;

/**
 * Represents a message for a user updated domain event.
 */
public class UserUpdatedMessage extends DomainEventMessage {

    /**
     * The unique identifier of the updated user.
     */
    private final String userId;
    /**
     * The name of the updated user.
     */
    private final String name;
    /**
     * The surname of the updated user.
     */
    private final String surname;

    /**
     * Constructs a new UserUpdatedMessage with the given event data.
     *
     * @param eventId   the unique identifier of the event
     * @param type      the type of the event
     * @param timestamp the timestamp of the event
     * @param userId    the unique identifier of the updated user
     * @param name      the name of the updated user
     * @param surname   the surname of the updated user
     */
    public UserUpdatedMessage(String eventId, String type, String timestamp,
            String userId, String name, String surname) {
        super(eventId, type, timestamp);
        this.userId = userId;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Returns the unique identifier of the updated user.
     *
     * @return the unique identifier of the updated user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the name of the updated user.
     *
     * @return the name of the updated user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the surname of the updated user.
     *
     * @return the surname of the updated user
     */
    public String getSurname() {
        return surname;
    }

}

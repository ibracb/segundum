package segundum.infrastructure.messaging.messages;

/**
 * Represents a message for a user deactivated domain event.
 */
public class UserDeactivatedMessage extends DomainEventMessage {

    /**
     * The unique identifier of the deactivated user.
     */
    private final String userId;

    /**
     * Constructs a new UserDeactivatedMessage with the given event data.
     *
     * @param eventId   the unique identifier of the event
     * @param type      the type of the event
     * @param timestamp the timestamp of the event
     * @param userId    the unique identifier of the deactivated user
     */
    public UserDeactivatedMessage(String eventId, String type, String timestamp, String userId) {
        super(eventId, type, timestamp);
        this.userId = userId;
    }

    /**
     * Returns the unique identifier of the deactivated user.
     *
     * @return the unique identifier of the deactivated user
     */
    public String getUserId() {
        return userId;
    }

}

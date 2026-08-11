package segundum.infrastructure.messaging.messages;

/**
 * Represents the base message for a domain event published to the messaging system.
 */
public abstract class DomainEventMessage {

    /**
     * The unique identifier of the event.
     */
    private final String eventId;
    /**
     * The type of the event.
     */
    private final String type;
    /**
     * The timestamp of the event.
     */
    private final String timestamp;

    /**
     * Constructs a new DomainEventMessage with the given event data.
     *
     * @param eventId   the unique identifier of the event
     * @param type      the type of the event
     * @param timestamp the timestamp of the event
     */
    public DomainEventMessage(String eventId, String type, String timestamp) {
        this.eventId = eventId;
        this.type = type;
        this.timestamp = timestamp;
    }

    /**
     * Returns the unique identifier of the event.
     *
     * @return the unique identifier of the event
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Returns the type of the event.
     *
     * @return the type of the event
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the timestamp of the event.
     *
     * @return the timestamp of the event
     */
    public String getTimestamp() {
        return timestamp;
    }

}

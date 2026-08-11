package segundum.infrastructure.messaging.messages;

/**
 * Represents the base class for domain event messages sent through the messaging infrastructure.
 */
public abstract class DomainEventMessage {

	/**
	 * The identifier of the event.
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
	 * Constructs a new DomainEventMessage with the given data.
	 *
	 * @param eventId the event identifier
	 * @param type the event type
	 * @param timestamp the event timestamp
	 */
	public DomainEventMessage(String eventId, String type, String timestamp) {
		this.eventId = eventId;
		this.type = type;
		this.timestamp = timestamp;
	}

	/**
	 * Returns the identifier of the event.
	 *
	 * @return the event identifier
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * Returns the type of the event.
	 *
	 * @return the event type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the timestamp of the event.
	 *
	 * @return the event timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

}

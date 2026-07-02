package segundum.domain.events;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents a domain event in the system.
 */
public abstract class DomainEvent {
	
	/**
	 * The unique identifier of the event.
	 */
	private final UUID eventId;
	
	/**
	 * The type of the event.
	 */
	private final Class<?> type;
	
	/**
	 * The timestamp of the event.
	 */
	private final Instant timestamp;
	
	/**
	 * Constructs a new DomainEvent with a unique identifier, type, and timestamp.
	 */
	DomainEvent() {
		this.eventId = UUID.randomUUID();
		this.type = getClass();
		this.timestamp = Instant.now();
	}
	
	/**
	 * Returns the unique identifier of the event.
	 * 
	 * @return the unique identifier of the event
	 */
	public UUID getEventId() {
		return eventId;
	}
	
	/**
	 * Returns the type of the event.
	 * 
	 * @return the type of the event
	 */
	public Class<?> getType() {
		return type;
	}
	
	/**
	 * Returns the timestamp of the event.
	 * 
	 * @return the timestamp of the event
	 */
	public Instant getTimestamp() {
		return timestamp;
	}

}

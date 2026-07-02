package segundum.domain.models.user;

import java.util.UUID;

/**
 * Represents a user's unique identifier.
 */
public class UserId {
	
	/**
	 * The value of the unique identifier.
	 */
	private final UUID value;
	
	/**
	 * Constructs a new Id object with a randomly generated UUID value.
	 */
	private UserId() {
		this.value = UUID.randomUUID();
	}
	
	/**
	 * Constructs a new Id object with the given UUID value.
	 * 
	 * @param value the UUID value
	 */
	private UserId(UUID value) {
		this.value = value;
	}
	
	/**
	 * Generates a new unique identifier.
	 * 
	 * @return a new Id object
	 */
	public static UserId generate() {
		return new UserId();
	}
	
	/**
	 * Creates a new UserId from a UUID string.
	 * 
	 * @param uuid the UUID string
	 * @return a new UserId object
	 */
	public static UserId fromString(String uuid) {
		return new UserId(UUID.fromString(uuid));
	}
	
	/**
	 * Returns the value of the unique identifier.
	 * 
	 * @return the value of the unique identifier
	 */
	public UUID getValue() {
		return value;
	}

}

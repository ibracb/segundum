package segundum.application.events.users;

import java.util.UUID;

/**
 * DTO representing the incoming event of a user being deactivated.
 */
public class UserDeactivated {

	/**
	 * The unique identifier of the user.
	 */
	private final UUID userId;

	/**
	 * Constructs a new UserDeactivated with the given parameters.
	 *
	 * @param userId the unique identifier of the user
	 */
	public UserDeactivated(UUID userId) {
		this.userId = userId;
	}

	/**
	 * Returns the unique identifier of the user.
	 *
	 * @return the unique identifier of the user
	 */
	public UUID getUserId() {
		return userId;
	}

}

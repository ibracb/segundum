package segundum.application.events.users;

import java.util.UUID;

/**
 * DTO representing the incoming event of a user being updated.
 */
public class UserUpdated {

	/**
	 * The unique identifier of the user.
	 */
	private final UUID userId;

	/**
	 * The name of the user.
	 */
	private final String name;

	/**
	 * The surname of the user.
	 */
	private final String surname;

	/**
	 * Constructs a new UserUpdated with the given parameters.
	 *
	 * @param userId the unique identifier of the user
	 * @param name the name of the user
	 * @param surname the surname of the user
	 */
	public UserUpdated(UUID userId, String name, String surname) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Returns the unique identifier of the user.
	 *
	 * @return the unique identifier of the user
	 */
	public UUID getUserId() {
		return userId;
	}

	/**
	 * Returns the name of the user.
	 *
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the surname of the user.
	 *
	 * @return the surname of the user
	 */
	public String getSurname() {
		return surname;
	}

}

package segundum.application.notifications.users;

import java.util.UUID;

/**
 * DTO representing the incoming event of a user being registered.
 */
public class UserRegisteredNotification {

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
	 * The email of the user.
	 */
	private final String email;

	/**
	 * Constructs a new UserRegisteredNotification with the given parameters.
	 *
	 * @param userId the unique identifier of the user
	 * @param name the name of the user
	 * @param surname the surname of the user
	 * @param email the email of the user
	 */
	public UserRegisteredNotification(UUID userId, String name, String surname, String email) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.email = email;
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

	/**
	 * Returns the email of the user.
	 *
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}

}

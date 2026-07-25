package segundum.application.commands;

import segundum.domain.models.user.UserId;

/**
 * Represents a command to deactivate an existing user.
 */
public class DeactivateUserCommand {

	/**
	 * The unique identifier of the user to be deactivated.
	 */
	private final UserId userId;

	/**
	 * Constructs a new DeactivateUserCommand with the given user identifier.
	 *
	 * @param userId the unique identifier of the user to be deactivated
	 */
	public DeactivateUserCommand(UserId userId) {
		this.userId = userId;
	}

	/**
	 * Returns the unique identifier of the user to be deactivated.
	 *
	 * @return the unique identifier of the user to be deactivated
	 */
	public UserId getUserId() {
		return userId;
	}

}

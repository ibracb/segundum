package segundum.application.commands;

import segundum.domain.models.user.UserId;

/**
 * Represents a command to delete an existing user from the system.
 */
public class DeleteUserCommand {

	/**
	 * The unique identifier of the user to be deleted.
	 */
	private final UserId userId;

	/**
	 * Constructs a new DeleteUserCommand with the given user identifier.
	 *
	 * @param userId the unique identifier of the user to be deleted
	 */
	public DeleteUserCommand(UserId userId) {
		this.userId = userId;
	}

	/**
	 * Returns the unique identifier of the user to be deleted.
	 *
	 * @return the unique identifier of the user to be deleted
	 */
	public UserId getUserId() {
		return userId;
	}

}

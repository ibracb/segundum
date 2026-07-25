package segundum.application.eventhandlers.users;

import segundum.application.events.users.UserDeactivated;

/**
 * Port interface for handling user deactivated events.
 */
public interface UserDeactivatedHandler {

	/**
	 * Handles the given user deactivated event.
	 *
	 * @param event the user deactivated event to handle
	 */
	void handle(UserDeactivated event);

}

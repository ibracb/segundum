package segundum.application.eventhandlers.users;

import segundum.application.events.users.UserUpdated;

/**
 * Port interface for handling user updated events.
 */
public interface UserUpdatedHandler {

	/**
	 * Handles the given user updated event.
	 *
	 * @param event the user updated event to handle
	 */
	void handle(UserUpdated event);

}

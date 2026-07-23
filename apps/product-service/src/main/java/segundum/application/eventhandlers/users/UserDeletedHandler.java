package segundum.application.eventhandlers.users;

import segundum.application.events.users.UserDeleted;

/**
 * Port interface for handling user deleted events.
 */
public interface UserDeletedHandler {

	/**
	 * Handles the given user deleted event.
	 *
	 * @param event the user deleted event to handle
	 */
	void handle(UserDeleted event);

}

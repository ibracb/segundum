package segundum.application.eventhandlers.users;

import segundum.application.events.users.UserRegistered;

/**
 * Port interface for handling user registered events.
 */
public interface UserRegisteredHandler {

	/**
	 * Handles the given user registered event.
	 *
	 * @param event the user registered event to handle
	 */
	void handle(UserRegistered event);

}

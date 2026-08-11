package segundum.application.eventhandlers;

import segundum.application.events.users.UserDeactivated;
import segundum.application.events.users.UserRegistered;
import segundum.application.events.users.UserUpdated;

/**
 * Port interface for handling events from the users bounded context.
 */
public interface UserEventHandler {

	/**
	 * Handles the given user registered event.
	 *
	 * @param event the user registered event to handle
	 */
	void onUserRegistered(UserRegistered event);

	/**
	 * Handles the given user updated event.
	 *
	 * @param event the user updated event to handle
	 */
	void onUserUpdated(UserUpdated event);

	/**
	 * Handles the given user deactivated event.
	 *
	 * @param event the user deactivated event to handle
	 */
	void onUserDeactivated(UserDeactivated event);

}

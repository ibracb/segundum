package segundum.application.notificationhandlers;

import segundum.application.notifications.users.UserDeactivatedNotification;
import segundum.application.notifications.users.UserRegisteredNotification;
import segundum.application.notifications.users.UserUpdatedNotification;

/**
 * Port interface for handling events from the users bounded context.
 */
public interface UserNotificationHandler {

	/**
	 * Handles the given user registered event.
	 *
	 * @param event the user registered event to handle
	 */
	void onUserRegisteredNotification(UserRegisteredNotification event);

	/**
	 * Handles the given user updated event.
	 *
	 * @param event the user updated event to handle
	 */
	void onUserUpdatedNotification(UserUpdatedNotification event);

	/**
	 * Handles the given user deactivated event.
	 *
	 * @param event the user deactivated event to handle
	 */
	void onUserDeactivatedNotification(UserDeactivatedNotification event);

}

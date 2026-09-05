package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.notificationhandlers.UserNotificationHandler;
import segundum.application.notifications.users.UserDeactivatedNotification;
import segundum.application.notifications.users.UserRegisteredNotification;
import segundum.application.notifications.users.UserUpdatedNotification;

/**
 * Represents the transaction boundary for the users bounded context event handlers.
 */
@Component
public class UserNotificationFacade {

	/**
	 * The user event handler.
	 */
	private final UserNotificationHandler handler;

	/**
	 * Constructs a new UserNotificationFacade with the given handler.
	 *
	 * @param handler the user event handler
	 */
	public UserNotificationFacade(UserNotificationHandler handler) {
		this.handler = handler;
	}

	/**
	 * Handles a user registered event within a single transaction.
	 *
	 * @param event the user registered event
	 */
	@Transactional
	public void onUserRegisteredNotification(UserRegisteredNotification event) {
		handler.onUserRegisteredNotification(event);
	}

	/**
	 * Handles a user updated event within a single transaction.
	 *
	 * @param event the user updated event
	 */
	@Transactional
	public void onUserUpdatedNotification(UserUpdatedNotification event) {
		handler.onUserUpdatedNotification(event);
	}

	/**
	 * Handles a user deactivated event within a single transaction.
	 *
	 * @param event the user deactivated event
	 */
	@Transactional
	public void onUserDeactivatedNotification(UserDeactivatedNotification event) {
		handler.onUserDeactivatedNotification(event);
	}

}

package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.eventhandlers.UserEventHandler;
import segundum.application.events.users.UserDeactivated;
import segundum.application.events.users.UserRegistered;
import segundum.application.events.users.UserUpdated;

/**
 * Represents the transaction boundary for the users bounded context event handlers.
 */
@Component
public class UserEventFacade {

	/**
	 * The user event handler.
	 */
	private final UserEventHandler handler;

	/**
	 * Constructs a new UserEventFacade with the given handler.
	 *
	 * @param handler the user event handler
	 */
	public UserEventFacade(UserEventHandler handler) {
		this.handler = handler;
	}

	/**
	 * Handles a user registered event within a single transaction.
	 *
	 * @param event the user registered event
	 */
	@Transactional
	public void onUserRegistered(UserRegistered event) {
		handler.onUserRegistered(event);
	}

	/**
	 * Handles a user updated event within a single transaction.
	 *
	 * @param event the user updated event
	 */
	@Transactional
	public void onUserUpdated(UserUpdated event) {
		handler.onUserUpdated(event);
	}

	/**
	 * Handles a user deactivated event within a single transaction.
	 *
	 * @param event the user deactivated event
	 */
	@Transactional
	public void onUserDeactivated(UserDeactivated event) {
		handler.onUserDeactivated(event);
	}

}

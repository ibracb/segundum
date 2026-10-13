package segundum.infrastructure.facades;

import segundum.application.notificationhandlers.RatingNotificationHandler;
import segundum.application.notifications.ratings.RatingCreatedNotification;

/**
 * Represents the transaction boundary for the ratings bounded context event handlers.
 */
public final class RatingNotificationFacade {

	/**
	 * The rating event handler.
	 */
	private final RatingNotificationHandler handler;

	/**
	 * Constructs a new RatingNotificationFacade with the given handler.
	 *
	 * @param handler the rating event handler
	 */
	public RatingNotificationFacade(RatingNotificationHandler handler) {
		this.handler = handler;
	}

	/**
	 * Handles a rating created event within a single transaction.
	 *
	 * @param event the rating created event
	 */
	public void onRatingCreatedNotification(RatingCreatedNotification event) {
		UnitOfWork.runVoid(() -> handler.onRatingCreatedNotification(event));
	}

}

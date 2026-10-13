package segundum.application.notificationhandlers;

import segundum.application.notifications.ratings.RatingCreatedNotification;

/**
 * Port interface for handling events from the ratings bounded context.
 */
public interface RatingNotificationHandler {

	/**
	 * Handles the given rating created event.
	 *
	 * @param event the rating created event to handle
	 */
	void onRatingCreatedNotification(RatingCreatedNotification event);

}

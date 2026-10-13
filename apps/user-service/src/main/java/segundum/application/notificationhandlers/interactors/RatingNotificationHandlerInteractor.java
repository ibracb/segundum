package segundum.application.notificationhandlers.interactors;

import java.util.Optional;

import segundum.application.notificationhandlers.RatingNotificationHandler;
import segundum.application.notifications.ratings.RatedUserRole;
import segundum.application.notifications.ratings.RatingCreatedNotification;
import segundum.application.outbound.LogEmitter;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.repositories.UserRepository;

/**
 * Interactor for handling events from the ratings bounded context.
 * <p>
 * When a rating is created, registers the rating on the rated user, updating
 * the number of ratings received and their average in the role (purchaser or
 * seller) the user had in the sale.
 * </p>
 */
public class RatingNotificationHandlerInteractor implements RatingNotificationHandler {

	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * The repository for managing users.
	 */
	private final UserRepository userRepository;

	/**
	 * Constructs a new RatingNotificationHandlerInteractor with the given dependencies.
	 *
	 * @param userRepository the repository for managing users
	 * @param logEmitter the log emitter
	 */
	public RatingNotificationHandlerInteractor(UserRepository userRepository, LogEmitter logEmitter) {
		this.userRepository = userRepository;
		this.logEmitter = logEmitter;
	}

	@Override
	public void onRatingCreatedNotification(RatingCreatedNotification event) {
		try {
			UserId ratedUserId = UserId.fromString(event.getRatedUserId().toString());
			Optional<User> ratedUser = userRepository.findById(ratedUserId);
			if (ratedUser.isEmpty()) {
				logEmitter.warn("User with ID " + event.getRatedUserId() + " not found. Event "
						+ event.getClass().getSimpleName() + " discarded.");
				return;
			}
			User user = ratedUser.get();
			if (event.getRatedUserRole() == RatedUserRole.PURCHASER) {
				user.registerRatingAsPurchaser(event.getScore());
			}
			else {
				user.registerRatingAsSeller(event.getScore());
			}
			userRepository.update(user);
		}
		catch (DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
		catch (Exception e) {
			logEmitter.error("Unexpected error processing " + event.getClass().getSimpleName() + ": " + e.getMessage(), e);
		}
	}

}

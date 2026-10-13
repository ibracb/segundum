package segundum.application.notifications.ratings;

import java.util.UUID;

/**
 * DTO representing the incoming event of a rating being created.
 */
public class RatingCreatedNotification {

	/**
	 * The unique identifier of the user that has been rated.
	 */
	private final UUID ratedUserId;

	/**
	 * The role of the rated user in the sale.
	 */
	private final RatedUserRole ratedUserRole;

	/**
	 * The score given to the rated user, between 1 and 5.
	 */
	private final int score;

	/**
	 * Constructs a new RatingCreatedNotification with the given parameters.
	 *
	 * @param ratedUserId the unique identifier of the user that has been rated
	 * @param ratedUserRole the role of the rated user in the sale
	 * @param score the score given to the rated user, between 1 and 5
	 */
	public RatingCreatedNotification(UUID ratedUserId, RatedUserRole ratedUserRole, int score) {
		this.ratedUserId = ratedUserId;
		this.ratedUserRole = ratedUserRole;
		this.score = score;
	}

	/**
	 * Returns the unique identifier of the user that has been rated.
	 *
	 * @return the unique identifier of the rated user
	 */
	public UUID getRatedUserId() {
		return ratedUserId;
	}

	/**
	 * Returns the role of the rated user in the sale.
	 *
	 * @return the role of the rated user
	 */
	public RatedUserRole getRatedUserRole() {
		return ratedUserRole;
	}

	/**
	 * Returns the score given to the rated user.
	 *
	 * @return the score given to the rated user
	 */
	public int getScore() {
		return score;
	}

}

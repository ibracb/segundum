package segundum.application.queries;

import segundum.domain.models.user.UserId;

/**
 * Represents a query to retrieve the ratings summary of a user from the system.
 */
public class GetUserRatingsQuery {

	/**
	 * The unique identifier of the user whose ratings summary is to be retrieved.
	 */
	private final UserId userId;

	/**
	 * Constructs a new GetUserRatingsQuery with the given user identifier.
	 *
	 * @param userId the unique identifier of the user whose ratings summary is to be retrieved
	 */
	public GetUserRatingsQuery(UserId userId) {
		this.userId = userId;
	}

	/**
	 * Returns the unique identifier of the user whose ratings summary is to be retrieved.
	 *
	 * @return the unique identifier of the user whose ratings summary is to be retrieved
	 */
	public UserId getUserId() {
		return userId;
	}

}

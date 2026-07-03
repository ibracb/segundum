package segundum.application.queries;

import segundum.domain.models.user.UserId;

/**
 * Represents a query to retrieve a user's profile from the system.
 */
public class GetUserProfileQuery {

	/**
	 * The unique identifier of the user whose profile is to be retrieved.
	 */
	private final UserId userId;

	/**
	 * Constructs a new GetUserProfileQuery with the given user identifier.
	 *
	 * @param userId the unique identifier of the user whose profile is to be retrieved
	 */
	public GetUserProfileQuery(UserId userId) {
		this.userId = userId;
	}

	/**
	 * Returns the unique identifier of the user whose profile is to be retrieved.
	 *
	 * @return the unique identifier of the user whose profile is to be retrieved
	 */
	public UserId getUserId() {
		return userId;
	}

}

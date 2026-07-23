package segundum.application.queries;

import segundum.domain.models.user.UserId;

/**
 * Represents a query to retrieve a user's statistics from the system.
 */
public class GetUserStatsQuery {

	/**
	 * The unique identifier of the user whose statistics are to be retrieved.
	 */
	private final UserId userId;

	/**
	 * Constructs a new GetUserStatsQuery with the given user identifier.
	 *
	 * @param userId the unique identifier of the user whose statistics are to be retrieved
	 */
	public GetUserStatsQuery(UserId userId) {
		this.userId = userId;
	}

	/**
	 * Returns the unique identifier of the user whose statistics are to be retrieved.
	 *
	 * @return the unique identifier of the user whose statistics are to be retrieved
	 */
	public UserId getUserId() {
		return userId;
	}

}

package segundum.application.usecases;

import segundum.application.queries.GetUserStatsQuery;
import segundum.domain.models.user.User;

/**
 * Represents the use case for retrieving a user's statistics.
 */
public interface GetUserStatsUseCase {

	/**
	 * Executes the use case to retrieve a user's statistics.
	 *
	 * @param query the query containing the identifier of the user whose statistics are to be retrieved
	 * @return the User object representing the user's statistics
	 */
	User execute(GetUserStatsQuery query);

}

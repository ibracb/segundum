package segundum.application.usecases;

import segundum.application.readmodels.user.UserStatsReadModel;
import segundum.application.queries.GetUserStatsQuery;

/**
 * Represents the use case for retrieving a user's statistics.
 */
public interface GetUserStatsUseCase {

	/**
	 * Executes the use case to retrieve a user's statistics.
	 *
	 * @param query the query containing the identifier of the user whose statistics are to be retrieved
	 * @return the user statistics read model
	 */
    UserStatsReadModel execute(GetUserStatsQuery query);

}

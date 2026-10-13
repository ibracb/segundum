package segundum.application.usecases;

import segundum.application.readmodels.user.UserRatingsReadModel;
import segundum.application.queries.GetUserRatingsQuery;

/**
 * Represents the use case for retrieving the ratings summary of a user.
 */
public interface GetUserRatingsUseCase {

	/**
	 * Executes the use case to retrieve the ratings summary of a user.
	 *
	 * @param query the query containing the identifier of the user whose ratings summary is to be retrieved
	 * @return the user ratings read model
	 */
    UserRatingsReadModel execute(GetUserRatingsQuery query);

}

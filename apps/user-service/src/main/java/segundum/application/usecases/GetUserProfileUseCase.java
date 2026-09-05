package segundum.application.usecases;

import segundum.application.readmodels.user.UserProfileReadModel;
import segundum.application.queries.GetUserProfileQuery;

/**
 * Represents the use case for retrieving a user's profile.
 */
public interface GetUserProfileUseCase {

	/**
	 * Executes the use case to retrieve a user's profile.
	 *
	 * @param query the query containing the identifier of the user whose profile is to be retrieved
	 * @return the user profile read model
	 */
    UserProfileReadModel execute(GetUserProfileQuery query);

}

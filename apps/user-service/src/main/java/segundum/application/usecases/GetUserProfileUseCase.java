package segundum.application.usecases;

import segundum.application.queries.GetUserProfileQuery;
import segundum.domain.models.user.User;

/**
 * Represents the use case for retrieving a user's profile.
 */
public interface GetUserProfileUseCase {

	/**
	 * Executes the use case to retrieve a user's profile.
	 *
	 * @param query the query containing the identifier of the user whose profile is to be retrieved
	 * @return the User object representing the user's profile
	 */
    User execute(GetUserProfileQuery query);

}

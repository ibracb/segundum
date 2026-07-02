package segundum.application.usecases;

import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;

/**
 * Represents the use case for retrieving a user's profile.
 */
public interface GetUserProfileUseCase {

	/**
	 * Executes the use case to retrieve a user's profile.
	 *
	 * @param userId The ID of the user whose profile is to be retrieved.
	 * @return The User object representing the user's profile.
	 */
    User execute(UserId userId);

}

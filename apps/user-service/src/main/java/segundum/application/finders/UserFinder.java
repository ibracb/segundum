package segundum.application.finders;

import java.util.List;
import java.util.Optional;

import segundum.application.readmodels.user.UserProfileReadModel;
import segundum.application.readmodels.user.UserStatsReadModel;
import segundum.application.readmodels.user.UserInfoReadModel;
import segundum.application.readmodels.user.UserNameReadModel;
import segundum.domain.models.user.UserId;

/**
 * Finder interface for querying user read models.
 */
public interface UserFinder {

	/**
	 * Finds the name data of a user by their identifier.
	 *
	 * @param id the user identifier
	 * @return the user name data, if it exists
	 */
	Optional<UserNameReadModel> findNameById(UserId id);

	/**
	 * Finds the profile data of a user by their identifier.
	 *
	 * @param id the user identifier
	 * @return the user profile data, if it exists
	 */
	Optional<UserProfileReadModel> findProfileById(UserId id);

	/**
	 * Finds the statistics data of a user by their identifier.
	 *
	 * @param id the user identifier
	 * @return the user statistics data, if it exists
	 */
	Optional<UserStatsReadModel> findStatsById(UserId id);

	/**
	 * Finds the info data of all users.
	 *
	 * @return the list of user info read models
	 */
	List<UserInfoReadModel> findAllUserInfo();

}

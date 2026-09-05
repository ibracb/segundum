package segundum.application.usecases;

import java.util.List;

import segundum.application.readmodels.user.UserInfoReadModel;
import segundum.application.queries.GetUserListQuery;

/**
 * Represents the use case for retrieving all user info.
 */
public interface GetUserListUseCase {

	/**
	 * Executes the use case to retrieve all user info.
	 *
	 * @param query the query
	 * @return the list of user info read models
	 */
	List<UserInfoReadModel> execute(GetUserListQuery query);

}

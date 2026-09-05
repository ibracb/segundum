package segundum.application.usecases;

import segundum.application.readmodels.user.UserNameReadModel;
import segundum.application.queries.GetUserNameQuery;

/**
 * Represents the use case for retrieving a user's name.
 */
public interface GetUserNameUseCase {

	/**
	 * Executes the use case to retrieve a user's name.
	 *
	 * @param query the query containing the identifier of the user whose name is to be retrieved
	 * @return the user name read model
	 */
    UserNameReadModel execute(GetUserNameQuery query);

}

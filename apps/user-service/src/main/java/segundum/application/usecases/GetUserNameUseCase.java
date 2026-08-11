package segundum.application.usecases;

import segundum.application.queries.GetUserNameQuery;
import segundum.domain.models.user.User;

/**
 * Represents the use case for retrieving the name of a user.
 */
public interface GetUserNameUseCase {

    /**
     * Executes the retrieval of a user by the given query.
     *
     * @param query the query containing the user identifier
     * @return the user whose name is being retrieved
     */
    User execute(GetUserNameQuery query);

}

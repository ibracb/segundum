package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.queries.GetUserNameQuery;
import segundum.application.usecases.GetUserNameUseCase;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.GetUserNameApi;
import segundum.infrastructure.rest.user.mappers.UserNameResponseMapper;

/**
 * Represents the controller that handles the retrieval of a user name.
 */
public class GetUserNameController implements GetUserNameApi {

    /**
     * The use case for retrieving a user name.
     */
    private final GetUserNameUseCase getUserNameUseCase;

    /**
     * Constructs a new GetUserNameController with the given use case.
     *
     * @param getUserNameUseCase the use case for retrieving a user name
     */
    public GetUserNameController(GetUserNameUseCase getUserNameUseCase) {
        this.getUserNameUseCase = getUserNameUseCase;
    }

    @Override
    public Response getUserName(String id) {
        GetUserNameQuery query = new GetUserNameQuery(UserId.fromString(id));
        User user = getUserNameUseCase.execute(query);
        return Response.ok(UserNameResponseMapper.fromDomain(user)).build();
    }

}

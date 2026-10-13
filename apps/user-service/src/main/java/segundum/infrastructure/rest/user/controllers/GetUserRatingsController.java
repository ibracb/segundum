package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.queries.GetUserRatingsQuery;
import segundum.application.readmodels.user.UserRatingsReadModel;
import segundum.application.usecases.GetUserRatingsUseCase;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.GetUserRatingsApi;
import segundum.infrastructure.rest.user.mappers.UserRatingsResponseMapper;

/**
 * Represents the controller that handles the retrieval of the ratings summary of a user.
 */
public class GetUserRatingsController implements GetUserRatingsApi {

	/**
	 * The use case for retrieving the ratings summary of a user.
	 */
	private final GetUserRatingsUseCase getUserRatingsUseCase;

	/**
	 * Constructs a new GetUserRatingsController with the given use case.
	 *
	 * @param getUserRatingsUseCase the use case for retrieving the ratings summary of a user
	 */
	public GetUserRatingsController(GetUserRatingsUseCase getUserRatingsUseCase) {
		this.getUserRatingsUseCase = getUserRatingsUseCase;
	}

	@Override
	public Response getUserRatings(String id) {
		GetUserRatingsQuery query = new GetUserRatingsQuery(UserId.fromString(id));
		UserRatingsReadModel ratings = getUserRatingsUseCase.execute(query);
		return Response.ok(UserRatingsResponseMapper.fromReadModel(ratings)).build();
	}

}

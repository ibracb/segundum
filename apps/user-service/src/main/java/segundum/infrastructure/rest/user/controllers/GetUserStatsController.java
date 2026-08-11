package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.queries.GetUserStatsQuery;
import segundum.application.usecases.GetUserStatsUseCase;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.GetUserStatsApi;
import segundum.infrastructure.rest.user.mappers.UserStatsResponseMapper;

/**
 * Represents the controller that handles the retrieval of user statistics.
 */
public class GetUserStatsController implements GetUserStatsApi {

	/**
	 * The use case for retrieving user statistics.
	 */
	private final GetUserStatsUseCase getUserStatsUseCase;

	/**
	 * Constructs a new GetUserStatsController with the given use case.
	 *
	 * @param getUserStatsUseCase the use case for retrieving user statistics
	 */
	public GetUserStatsController(GetUserStatsUseCase getUserStatsUseCase) {
		this.getUserStatsUseCase = getUserStatsUseCase;
	}

	@Override
	public Response getUserStats(String id) {
		GetUserStatsQuery query = new GetUserStatsQuery(UserId.fromString(id));
		User user = getUserStatsUseCase.execute(query);
		return Response.ok(UserStatsResponseMapper.fromDomain(user)).build();
	}

}

package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.queries.GetUserStatsQuery;
import segundum.application.usecases.GetUserStatsUseCase;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.GetUserStatsApi;
import segundum.infrastructure.rest.user.mappers.UserStatsResponseMapper;

public class GetUserStatsController implements GetUserStatsApi {

	private final GetUserStatsUseCase getUserStatsUseCase;

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

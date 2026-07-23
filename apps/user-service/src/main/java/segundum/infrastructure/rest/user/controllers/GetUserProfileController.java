package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.queries.GetUserProfileQuery;
import segundum.application.usecases.GetUserProfileUseCase;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.GetUserProfileApi;
import segundum.infrastructure.rest.user.mappers.UserProfileResponseMapper;

public class GetUserProfileController implements GetUserProfileApi {

	private final GetUserProfileUseCase getUserProfileUseCase;

	public GetUserProfileController(GetUserProfileUseCase getUserProfileUseCase) {
		this.getUserProfileUseCase = getUserProfileUseCase;
	}

	@Override
	public Response getUserProfile(String id) {
		GetUserProfileQuery query = new GetUserProfileQuery(UserId.fromString(id));
		User user = getUserProfileUseCase.execute(query);
		return Response.ok(UserProfileResponseMapper.fromDomain(user)).build();
	}

}

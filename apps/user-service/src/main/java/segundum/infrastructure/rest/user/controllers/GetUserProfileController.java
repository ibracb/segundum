package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.queries.GetUserProfileQuery;
import segundum.application.usecases.GetUserProfileUseCase;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.GetUserProfileApi;
import segundum.infrastructure.rest.user.mappers.UserProfileResponseMapper;

/**
 * Represents the controller that handles the retrieval of a user profile.
 */
public class GetUserProfileController implements GetUserProfileApi {

	/**
	 * The use case for retrieving a user profile.
	 */
	private final GetUserProfileUseCase getUserProfileUseCase;

	/**
	 * Constructs a new GetUserProfileController with the given use case.
	 *
	 * @param getUserProfileUseCase the use case for retrieving a user profile
	 */
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

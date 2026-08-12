package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.UpdateUserCommand;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.facades.UpdateUserProfileFacade;
import segundum.infrastructure.rest.user.api.UpdateUserProfileApi;
import segundum.infrastructure.rest.user.mappers.UserProfileResponseMapper;
import segundum.infrastructure.rest.user.requests.UpdateUserProfileRequest;

/**
 * Represents the controller that handles the update of a user profile.
 */
public class UpdateUserProfileController implements UpdateUserProfileApi {

	/**
	 * The facade for updating a user profile.
	 */
	private final UpdateUserProfileFacade facade;

	/**
	 * Constructs a new UpdateUserProfileController with the given facade.
	 *
	 * @param facade the facade for updating a user profile
	 */
	public UpdateUserProfileController(UpdateUserProfileFacade facade) {
		this.facade = facade;
	}

	@Override
	public Response updateUserProfile(String id, UpdateUserProfileRequest request) {
		Name name = request.getName() != null ? new Name(request.getName()) : null;
		Surname surname = request.getSurname() != null ? new Surname(request.getSurname()) : null;
		Password password = request.getPassword() != null ? Password.plain(request.getPassword()) : null;
		Phone phone = request.getPhone() != null ? new Phone(request.getPhone()) : null;
		UpdateUserCommand command = new UpdateUserCommand(
				UserId.fromString(id),
				name,
				surname,
				password,
				phone
		);
		User user = facade.run(command);
		return Response.ok(UserProfileResponseMapper.fromDomain(user)).build();
	}

}

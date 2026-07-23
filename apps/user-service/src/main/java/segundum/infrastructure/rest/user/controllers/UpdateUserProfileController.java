package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.UpdateUserCommand;
import segundum.application.usecases.UpdateUserProfileUseCase;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.UpdateUserProfileApi;
import segundum.infrastructure.rest.user.mappers.UserProfileResponseMapper;
import segundum.infrastructure.rest.user.requests.UpdateUserProfileRequest;

public class UpdateUserProfileController implements UpdateUserProfileApi {

	private final UpdateUserProfileUseCase updateUserUseCase;

	public UpdateUserProfileController(UpdateUserProfileUseCase updateUserUseCase) {
		this.updateUserUseCase = updateUserUseCase;
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
		User user = updateUserUseCase.execute(command);
		return Response.ok(UserProfileResponseMapper.fromDomain(user)).build();
	}

}

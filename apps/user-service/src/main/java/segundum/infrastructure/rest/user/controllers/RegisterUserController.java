package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.RegisterUserCommand;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.infrastructure.facades.RegisterUserFacade;
import segundum.infrastructure.rest.user.api.RegisterUserApi;
import segundum.infrastructure.rest.user.mappers.UserProfileResponseMapper;
import segundum.infrastructure.rest.user.requests.RegisterUserRequest;

/**
 * Represents the controller that handles the registration of a new user.
 */
public class RegisterUserController implements RegisterUserApi {

	/**
	 * The facade for registering a user.
	 */
	private final RegisterUserFacade facade;

	/**
	 * Constructs a new RegisterUserController with the given facade.
	 *
	 * @param facade the facade for registering a user
	 */
	public RegisterUserController(RegisterUserFacade facade) {
		this.facade = facade;
	}

	@Override
	public Response registerUser(RegisterUserRequest request) {
		RegisterUserCommand command = new RegisterUserCommand(
				new Name(request.getName()),
				new Surname(request.getSurname()),
				new Email(request.getEmail()),
				Password.plain(request.getPassword()),
				new Birthdate(request.getBirthdate()),
				new Phone(request.getPhone())
		);
		User user = facade.run(command);
		return Response.status(Response.Status.CREATED)
				.entity(UserProfileResponseMapper.fromDomain(user))
				.build();
	}

}

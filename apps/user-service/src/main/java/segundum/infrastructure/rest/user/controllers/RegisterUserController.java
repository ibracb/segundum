package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.domain.models.user.Birthdate;
import segundum.domain.models.user.Email;
import segundum.domain.models.user.Name;
import segundum.domain.models.user.Password;
import segundum.domain.models.user.Phone;
import segundum.domain.models.user.Surname;
import segundum.domain.models.user.User;
import segundum.infrastructure.rest.user.api.RegisterUserApi;
import segundum.infrastructure.rest.user.mappers.UserProfileResponseMapper;
import segundum.infrastructure.rest.user.requests.RegisterUserRequest;

/**
 * Represents the controller that handles the registration of a new user.
 */
public class RegisterUserController implements RegisterUserApi {

	/**
	 * The use case for registering a new user.
	 */
	private final RegisterUserUseCase registerUserUseCase;

	/**
	 * Constructs a new RegisterUserController with the given use case.
	 *
	 * @param registerUserUseCase the use case for registering a new user
	 */
	public RegisterUserController(RegisterUserUseCase registerUserUseCase) {
		this.registerUserUseCase = registerUserUseCase;
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
		User user = registerUserUseCase.execute(command);
		return Response.status(Response.Status.CREATED)
				.entity(UserProfileResponseMapper.fromDomain(user))
				.build();
	}

}

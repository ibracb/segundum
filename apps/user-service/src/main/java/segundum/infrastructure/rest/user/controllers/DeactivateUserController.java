package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.DeactivateUserCommand;
import segundum.application.usecases.DeactivateUserUseCase;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.DeactivateUserApi;

/**
 * Represents the controller that handles the deactivation of a user.
 */
public class DeactivateUserController implements DeactivateUserApi {

	/**
	 * The use case for deactivating a user.
	 */
	private final DeactivateUserUseCase deactivateUserUseCase;

	/**
	 * Constructs a new DeactivateUserController with the given use case.
	 *
	 * @param deactivateUserUseCase the use case for deactivating a user
	 */
	public DeactivateUserController(DeactivateUserUseCase deactivateUserUseCase) {
		this.deactivateUserUseCase = deactivateUserUseCase;
	}

	@Override
	public Response deactivateUser(String id) {
		DeactivateUserCommand command = new DeactivateUserCommand(UserId.fromString(id));
		deactivateUserUseCase.execute(command);
		return Response.noContent().build();
	}

}

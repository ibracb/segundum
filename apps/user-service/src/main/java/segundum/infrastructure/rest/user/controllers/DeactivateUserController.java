package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.DeactivateUserCommand;
import segundum.application.usecases.DeactivateUserUseCase;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.DeactivateUserApi;

public class DeactivateUserController implements DeactivateUserApi {

	private final DeactivateUserUseCase deactivateUserUseCase;

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

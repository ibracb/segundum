package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.DeleteUserCommand;
import segundum.application.usecases.DeleteUserUseCase;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.rest.user.api.DeleteUserApi;

public class DeleteUserController implements DeleteUserApi {

	private final DeleteUserUseCase deleteUserUseCase;

	public DeleteUserController(DeleteUserUseCase deleteUserUseCase) {
		this.deleteUserUseCase = deleteUserUseCase;
	}

	@Override
	public Response deleteUser(String id) {
		DeleteUserCommand command = new DeleteUserCommand(UserId.fromString(id));
		deleteUserUseCase.execute(command);
		return Response.noContent().build();
	}

}

package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.DeactivateUserCommand;
import segundum.domain.models.user.UserId;
import segundum.infrastructure.facades.DeactivateUserFacade;
import segundum.infrastructure.rest.user.api.DeactivateUserApi;

/**
 * Represents the controller that handles the deactivation of a user.
 */
public class DeactivateUserController implements DeactivateUserApi {

	/**
	 * The facade for deactivating a user.
	 */
	private final DeactivateUserFacade facade;

	/**
	 * Constructs a new DeactivateUserController with the given facade.
	 *
	 * @param facade the facade for deactivating a user
	 */
	public DeactivateUserController(DeactivateUserFacade facade) {
		this.facade = facade;
	}

	@Override
	public Response deactivateUser(String id) {
		DeactivateUserCommand command = new DeactivateUserCommand(UserId.fromString(id));
		facade.run(command);
		return Response.noContent().build();
	}

}

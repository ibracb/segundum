package segundum.infrastructure.rest.user.controllers;

import javax.ws.rs.core.Response;

import segundum.application.commands.AuthenticateUserCommand;
import segundum.application.readmodels.user.AuthenticatedUserReadModel;
import segundum.domain.models.user.Email;
import segundum.infrastructure.facades.AuthenticateUserFacade;
import segundum.infrastructure.rest.user.api.AuthenticateUserApi;
import segundum.infrastructure.rest.user.requests.AuthenticateUserRequest;

/**
 * Controller for authenticating a user.
 */
public class AuthenticateUserController implements AuthenticateUserApi {

    /**
     * The facade for authenticating a user.
     */
    private final AuthenticateUserFacade facade;

    /**
     * Constructs a new AuthenticateUserController with the given facade.
     *
     * @param facade the facade for authenticating a user
     */
    public AuthenticateUserController(AuthenticateUserFacade facade) {
        this.facade = facade;
    }

    @Override
    public Response authenticateUser(AuthenticateUserRequest request) {
        request.validate();
        AuthenticateUserCommand command = new AuthenticateUserCommand(
                new Email(request.getEmail()),
                request.getPassword());
        AuthenticatedUserReadModel result = facade.run(command);
        return Response.ok(result).build();
    }

}

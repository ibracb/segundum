package segundum.infrastructure.facades;

import segundum.application.commands.AuthenticateUserCommand;
import segundum.application.readmodels.user.AuthenticatedUserReadModel;
import segundum.application.usecases.AuthenticateUserUseCase;

/**
 * Transaction boundary for authenticating a user.
 */
public final class AuthenticateUserFacade {

    /**
     * The use case for authenticating a user.
     */
    private final AuthenticateUserUseCase useCase;

    /**
     * Constructs a new AuthenticateUserFacade with the given use case.
     *
     * @param useCase the use case for authenticating a user
     */
    public AuthenticateUserFacade(AuthenticateUserUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Authenticates a user within a single transaction.
     *
     * @param command the authenticate user command
     * @return the authenticated user read model
     */
    public AuthenticatedUserReadModel run(AuthenticateUserCommand command) {
        return UnitOfWork.run(() -> useCase.execute(command));
    }

}

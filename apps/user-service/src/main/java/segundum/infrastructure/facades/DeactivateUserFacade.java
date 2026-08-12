package segundum.infrastructure.facades;

import segundum.application.commands.DeactivateUserCommand;
import segundum.application.usecases.DeactivateUserUseCase;

/**
 * Represents the transaction boundary for deactivating a user.
 */
public final class DeactivateUserFacade {

	/**
	 * The use case for deactivating a user.
	 */
	private final DeactivateUserUseCase useCase;

	/**
	 * Constructs a new DeactivateUserFacade with the given use case.
	 *
	 * @param useCase the use case for deactivating a user
	 */
	public DeactivateUserFacade(DeactivateUserUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Deactivates a user within a single transaction.
	 *
	 * @param command the deactivate user command
	 */
	public void run(DeactivateUserCommand command) {
		UnitOfWork.runVoid(() -> useCase.execute(command));
	}

}

package segundum.infrastructure.facades;

import segundum.application.commands.UpdateUserCommand;
import segundum.application.usecases.UpdateUserProfileUseCase;

/**
 * Represents the transaction boundary for updating a user profile.
 */
public final class UpdateUserProfileFacade {

	/**
	 * The use case for updating a user profile.
	 */
	private final UpdateUserProfileUseCase useCase;

	/**
	 * Constructs a new UpdateUserProfileFacade with the given use case.
	 *
	 * @param useCase the use case for updating a user profile
	 */
	public UpdateUserProfileFacade(UpdateUserProfileUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Updates a user profile within a single transaction.
	 *
	 * @param command the update user command
	 */
	public void run(UpdateUserCommand command) {
		UnitOfWork.runVoid(() -> useCase.execute(command));
	}

}

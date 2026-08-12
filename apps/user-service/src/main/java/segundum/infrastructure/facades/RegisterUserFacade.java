package segundum.infrastructure.facades;

import segundum.application.commands.RegisterUserCommand;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.domain.models.user.User;

/**
 * Represents the transaction boundary for registering a user.
 */
public final class RegisterUserFacade {

	/**
	 * The use case for registering a user.
	 */
	private final RegisterUserUseCase useCase;

	/**
	 * Constructs a new RegisterUserFacade with the given use case.
	 *
	 * @param useCase the use case for registering a user
	 */
	public RegisterUserFacade(RegisterUserUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Registers a user within a single transaction.
	 *
	 * @param command the register user command
	 * @return the registered user
	 */
	public User run(RegisterUserCommand command) {
		return UnitOfWork.run(() -> useCase.execute(command));
	}

}

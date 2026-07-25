package segundum.application.usecases;

import segundum.application.commands.DeactivateUserCommand;

/**
 * Represents the use case for deactivating an existing user in the system.
 */
public interface DeactivateUserUseCase {
	
	/**
	 * Executes the use case to deactivate an existing user in the system.
	 * 
	 * @param command the command containing the identifier of the user to be deactivated
	 */
	void execute(DeactivateUserCommand command);

}

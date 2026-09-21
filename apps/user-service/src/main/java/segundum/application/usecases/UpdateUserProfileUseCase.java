package segundum.application.usecases;

import segundum.application.commands.UpdateUserCommand;

/**
 * Represents the use case for updating an existing user in the system.
 */
public interface UpdateUserProfileUseCase {
	
	/**
	 * Executes the use case to update an existing user in the system.
	 * 
	 * @param command the command containing the information to update the user
	 */
	void execute(UpdateUserCommand command);

}

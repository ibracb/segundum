package segundum.application.usecases;

import segundum.application.commands.DeleteUserCommand;

/**
 * Represents the use case for deleting an existing user in the system.
 */
public interface DeleteUserUseCase {
	
	/**
	 * Executes the use case to delete an existing user in the system.
	 * 
	 * @param command the command containing the identifier of the user to be deleted
	 */
	void execute(DeleteUserCommand command);

}

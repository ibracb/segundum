package segundum.application.usecases;

import segundum.application.commands.UpdateUserCommand;
import segundum.domain.models.user.User;

/**
 * Represents the use case for updating an existing user in the system.
 */
public interface UpdateUserProfileUseCase {
	
	/**
	 * Executes the use case to update an existing user in the system.
	 * 
	 * @param command the command containing the information to update the user
	 * @return the updated user
	 */
	User execute(UpdateUserCommand command);

}

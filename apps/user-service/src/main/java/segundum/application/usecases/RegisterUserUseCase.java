package segundum.application.usecases;

import segundum.application.commands.RegisterUserCommand;
import segundum.domain.models.user.User;

/**
 * Represents the use case for creating a new user in the system.
 */
public interface RegisterUserUseCase {
	
	/**
	 * Executes the use case to create a new user in the system.
	 * 
	 * @param command the command containing the information to create the user
	 * @return the created user
	 */
	User execute(RegisterUserCommand command);

}

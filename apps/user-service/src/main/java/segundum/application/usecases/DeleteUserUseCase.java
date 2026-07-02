package segundum.application.usecases;

import segundum.domain.models.user.UserId;

/**
 * Represents the use case for deleting an existing user in the system.
 */
public interface DeleteUserUseCase {
	
	/**
	 * Executes the use case to delete an existing user in the system.
	 * 
	 * @param userId the unique identifier of the user to be deleted
	 */
	void execute(UserId userId);

}

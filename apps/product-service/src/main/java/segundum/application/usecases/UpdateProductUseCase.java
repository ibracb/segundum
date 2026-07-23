package segundum.application.usecases;

import segundum.application.commands.UpdateProductCommand;

/**
 * Represents the use case for updating an existing product in the system.
 */
public interface UpdateProductUseCase {

	/**
	 * Executes the use case to update an existing product in the system.
	 *
	 * @param command the command containing the information to update the product
	 */
	void execute(UpdateProductCommand command);

}

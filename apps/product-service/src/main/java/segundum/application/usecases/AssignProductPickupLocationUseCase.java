package segundum.application.usecases;

import segundum.application.commands.AssignProductPickupLocationCommand;

/**
 * Represents the use case for assigning a pickup location to a product.
 */
public interface AssignProductPickupLocationUseCase {

	/**
	 * Executes the use case to assign a pickup location to a product.
	 *
	 * @param command the command containing the product identifier and pickup location
	 */
	void execute(AssignProductPickupLocationCommand command);

}

package segundum.application.usecases;

import segundum.application.commands.RemoveProductCommand;

/**
 * Use case to permanently remove a product that is for sale.
 */
public interface RemoveProductUseCase {

	void execute(RemoveProductCommand command);

}

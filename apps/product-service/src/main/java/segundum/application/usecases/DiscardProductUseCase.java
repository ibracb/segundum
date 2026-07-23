package segundum.application.usecases;

import segundum.application.commands.DiscardProductCommand;

/**
 * Use case to discard a draft product.
 */
public interface DiscardProductUseCase {

	void execute(DiscardProductCommand command);

}

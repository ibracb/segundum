package segundum.application.usecases;

import segundum.application.commands.IncrementProductViewsCommand;

/**
 * Represents the use case for incrementing the views of a product.
 */
public interface IncrementProductViewsUseCase {

	/**
	 * Executes the use case to increment the views of a product.
	 *
	 * @param command the command containing the product identifier
	 */
	void execute(IncrementProductViewsCommand command);

}

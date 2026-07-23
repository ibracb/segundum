package segundum.application.usecases;

import segundum.application.commands.CreateProductCommand;
import segundum.domain.models.product.ProductId;

/**
 * Represents the use case for creating a new product in the system.
 */
public interface CreateProductUseCase {

	/**
	 * Executes the use case to create a new product in the system.
	 *
	 * @param command the command containing the information to create the product
	 * @return the identifier of the created product
	 */
	ProductId execute(CreateProductCommand command);

}

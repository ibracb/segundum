package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.UpdateProductCommand;
import segundum.application.usecases.UpdateProductUseCase;

/**
 * Represents the transaction boundary for updating a product.
 */
@Component
public class UpdateProductFacade {

	/**
	 * The use case for updating a product.
	 */
	private final UpdateProductUseCase useCase;

	/**
	 * Constructs a new UpdateProductFacade with the given use case.
	 *
	 * @param useCase the use case for updating a product
	 */
	public UpdateProductFacade(UpdateProductUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Updates a product within a single transaction.
	 *
	 * @param command the update product command
	 */
	@Transactional
	public void run(UpdateProductCommand command) {
		useCase.execute(command);
	}

}

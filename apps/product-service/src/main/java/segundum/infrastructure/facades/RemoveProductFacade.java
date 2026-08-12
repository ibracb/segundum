package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.RemoveProductCommand;
import segundum.application.usecases.RemoveProductUseCase;

/**
 * Represents the transaction boundary for removing a product.
 */
@Component
public class RemoveProductFacade {

	/**
	 * The use case for removing a product.
	 */
	private final RemoveProductUseCase useCase;

	/**
	 * Constructs a new RemoveProductFacade with the given use case.
	 *
	 * @param useCase the use case for removing a product
	 */
	public RemoveProductFacade(RemoveProductUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Removes a product within a single transaction.
	 *
	 * @param command the remove product command
	 */
	@Transactional
	public void run(RemoveProductCommand command) {
		useCase.execute(command);
	}

}

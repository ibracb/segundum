package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.DiscardProductCommand;
import segundum.application.usecases.DiscardProductUseCase;

/**
 * Represents the transaction boundary for discarding a product.
 */
@Component
public class DiscardProductFacade {

	/**
	 * The use case for discarding a product.
	 */
	private final DiscardProductUseCase useCase;

	/**
	 * Constructs a new DiscardProductFacade with the given use case.
	 *
	 * @param useCase the use case for discarding a product
	 */
	public DiscardProductFacade(DiscardProductUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Discards a product within a single transaction.
	 *
	 * @param command the discard product command
	 */
	@Transactional
	public void run(DiscardProductCommand command) {
		useCase.execute(command);
	}

}

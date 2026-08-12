package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.PutProductForSaleCommand;
import segundum.application.usecases.PutProductForSaleUseCase;

/**
 * Represents the transaction boundary for putting a product for sale.
 */
@Component
public class PutProductForSaleFacade {

	/**
	 * The use case for putting a product for sale.
	 */
	private final PutProductForSaleUseCase useCase;

	/**
	 * Constructs a new PutProductForSaleFacade with the given use case.
	 *
	 * @param useCase the use case for putting a product for sale
	 */
	public PutProductForSaleFacade(PutProductForSaleUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Puts a product for sale within a single transaction.
	 *
	 * @param command the put product for sale command
	 */
	@Transactional
	public void run(PutProductForSaleCommand command) {
		useCase.execute(command);
	}

}

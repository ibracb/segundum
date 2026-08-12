package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.CompleteSaleCommand;
import segundum.application.usecases.CompleteSaleUseCase;

/**
 * Represents the transaction boundary for completing a sale.
 */
@Component
public class CompleteSaleFacade {

	/**
	 * The use case for completing a sale.
	 */
	private final CompleteSaleUseCase useCase;

	/**
	 * Constructs a new CompleteSaleFacade with the given use case.
	 *
	 * @param useCase the use case for completing a sale
	 */
	public CompleteSaleFacade(CompleteSaleUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Completes a sale within a single transaction.
	 *
	 * @param command the complete sale command
	 */
	@Transactional
	public void run(CompleteSaleCommand command) {
		useCase.execute(command);
	}

}

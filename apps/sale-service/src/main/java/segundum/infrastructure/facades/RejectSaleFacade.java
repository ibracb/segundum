package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.RejectSaleCommand;
import segundum.application.usecases.RejectSaleUseCase;

/**
 * Represents the transaction boundary for rejecting a sale.
 */
@Component
public class RejectSaleFacade {

	/**
	 * The use case for rejecting a sale.
	 */
	private final RejectSaleUseCase useCase;

	/**
	 * Constructs a new RejectSaleFacade with the given use case.
	 *
	 * @param useCase the use case for rejecting a sale
	 */
	public RejectSaleFacade(RejectSaleUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Rejects a sale within a single transaction.
	 *
	 * @param command the reject sale command
	 */
	@Transactional
	public void run(RejectSaleCommand command) {
		useCase.execute(command);
	}

}

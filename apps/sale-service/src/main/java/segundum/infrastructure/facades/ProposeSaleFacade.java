package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.ProposeSaleCommand;
import segundum.application.usecases.ProposeSaleUseCase;
import segundum.domain.models.sale.SaleId;

/**
 * Represents the transaction boundary for proposing a new sale.
 */
@Component
public class ProposeSaleFacade {

	/**
	 * The use case for proposing a sale.
	 */
	private final ProposeSaleUseCase useCase;

	/**
	 * Constructs a new ProposeSaleFacade with the given use case.
	 *
	 * @param useCase the use case for proposing a sale
	 */
	public ProposeSaleFacade(ProposeSaleUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Proposes a new sale within a single transaction.
	 *
	 * @param command the propose sale command
	 * @return the identifier of the proposed sale
	 */
	@Transactional
	public SaleId run(ProposeSaleCommand command) {
		return useCase.execute(command);
	}

}

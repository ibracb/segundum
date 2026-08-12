package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.CancelSaleBySellerCommand;
import segundum.application.usecases.CancelSaleBySellerUseCase;

/**
 * Represents the transaction boundary for cancelling a sale by its seller.
 */
@Component
public class CancelSaleBySellerFacade {

	/**
	 * The use case for cancelling a sale by its seller.
	 */
	private final CancelSaleBySellerUseCase useCase;

	/**
	 * Constructs a new CancelSaleBySellerFacade with the given use case.
	 *
	 * @param useCase the use case for cancelling a sale by its seller
	 */
	public CancelSaleBySellerFacade(CancelSaleBySellerUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Cancels a sale by its seller within a single transaction.
	 *
	 * @param command the cancel sale by seller command
	 */
	@Transactional
	public void run(CancelSaleBySellerCommand command) {
		useCase.execute(command);
	}

}

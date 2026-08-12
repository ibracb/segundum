package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.CancelSaleByPurchaserCommand;
import segundum.application.usecases.CancelSaleByPurchaserUseCase;

/**
 * Represents the transaction boundary for cancelling a sale by its purchaser.
 */
@Component
public class CancelSaleByPurchaserFacade {

	/**
	 * The use case for cancelling a sale by its purchaser.
	 */
	private final CancelSaleByPurchaserUseCase useCase;

	/**
	 * Constructs a new CancelSaleByPurchaserFacade with the given use case.
	 *
	 * @param useCase the use case for cancelling a sale by its purchaser
	 */
	public CancelSaleByPurchaserFacade(CancelSaleByPurchaserUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Cancels a sale by its purchaser within a single transaction.
	 *
	 * @param command the cancel sale by purchaser command
	 */
	@Transactional
	public void run(CancelSaleByPurchaserCommand command) {
		useCase.execute(command);
	}

}

package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.ReserveSaleCommand;
import segundum.application.usecases.ReserveSaleUseCase;

/**
 * Represents the transaction boundary for reserving a sale.
 */
@Component
public class ReserveSaleFacade {

	/**
	 * The use case for reserving a sale.
	 */
	private final ReserveSaleUseCase useCase;

	/**
	 * Constructs a new ReserveSaleFacade with the given use case.
	 *
	 * @param useCase the use case for reserving a sale
	 */
	public ReserveSaleFacade(ReserveSaleUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Reserves a sale within a single transaction.
	 *
	 * @param command the reserve sale command
	 */
	@Transactional
	public void run(ReserveSaleCommand command) {
		useCase.execute(command);
	}

}

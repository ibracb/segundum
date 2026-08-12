package segundum.infrastructure.facades;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import segundum.application.commands.TakeProductDownCommand;
import segundum.application.usecases.TakeProductDownUseCase;

/**
 * Represents the transaction boundary for taking a product down from sale.
 */
@Component
public class TakeProductDownFacade {

	/**
	 * The use case for taking a product down from sale.
	 */
	private final TakeProductDownUseCase useCase;

	/**
	 * Constructs a new TakeProductDownFacade with the given use case.
	 *
	 * @param useCase the use case for taking a product down from sale
	 */
	public TakeProductDownFacade(TakeProductDownUseCase useCase) {
		this.useCase = useCase;
	}

	/**
	 * Takes a product down from sale within a single transaction.
	 *
	 * @param command the take product down command
	 */
	@Transactional
	public void run(TakeProductDownCommand command) {
		useCase.execute(command);
	}

}

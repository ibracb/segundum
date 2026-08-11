package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.TakeProductDownCommand;
import segundum.application.usecases.TakeProductDownUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.TakeProductDownApi;

/**
 * Represents the controller for taking a product down from sale.
 */
@RestController
public class TakeProductDownController implements TakeProductDownApi {

	/**
	 * The take product down use case.
	 */
	private final TakeProductDownUseCase takeProductDownUseCase;

	/**
	 * Constructs a new TakeProductDownController with the given dependencies.
	 *
	 * @param takeProductDownUseCase the take product down use case
	 */
	public TakeProductDownController(TakeProductDownUseCase takeProductDownUseCase) {
		this.takeProductDownUseCase = takeProductDownUseCase;
	}

	@Override
	public ResponseEntity<Void> takeProductDown(String id) {
		takeProductDownUseCase.execute(new TakeProductDownCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

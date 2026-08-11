package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.DiscardProductCommand;
import segundum.application.usecases.DiscardProductUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.DiscardProductApi;

/**
 * Represents the controller for discarding a draft product.
 */
@RestController
public class DiscardProductController implements DiscardProductApi {

	/**
	 * The discard product use case.
	 */
	private final DiscardProductUseCase discardProductUseCase;

	/**
	 * Constructs a new DiscardProductController with the given dependencies.
	 *
	 * @param discardProductUseCase the discard product use case
	 */
	public DiscardProductController(DiscardProductUseCase discardProductUseCase) {
		this.discardProductUseCase = discardProductUseCase;
	}

	@Override
	public ResponseEntity<Void> discardProduct(String id) {
		discardProductUseCase.execute(new DiscardProductCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

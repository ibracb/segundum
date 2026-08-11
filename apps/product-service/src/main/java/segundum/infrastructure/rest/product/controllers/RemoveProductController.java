package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.RemoveProductCommand;
import segundum.application.usecases.RemoveProductUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.RemoveProductApi;

/**
 * Represents the controller for removing a product.
 */
@RestController
public class RemoveProductController implements RemoveProductApi {

	/**
	 * The remove product use case.
	 */
	private final RemoveProductUseCase removeProductUseCase;

	/**
	 * Constructs a new RemoveProductController with the given dependencies.
	 *
	 * @param removeProductUseCase the remove product use case
	 */
	public RemoveProductController(RemoveProductUseCase removeProductUseCase) {
		this.removeProductUseCase = removeProductUseCase;
	}

	@Override
	public ResponseEntity<Void> removeProduct(String id) {
		removeProductUseCase.execute(new RemoveProductCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

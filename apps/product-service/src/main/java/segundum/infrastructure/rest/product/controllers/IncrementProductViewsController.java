package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.IncrementProductViewsCommand;
import segundum.application.usecases.IncrementProductViewsUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.IncrementProductViewsApi;

/**
 * Represents the controller for incrementing the views of a product.
 */
@RestController
public class IncrementProductViewsController implements IncrementProductViewsApi {

	/**
	 * The increment product views use case.
	 */
	private final IncrementProductViewsUseCase incrementProductViewsUseCase;

	/**
	 * Constructs a new IncrementProductViewsController with the given dependencies.
	 *
	 * @param incrementProductViewsUseCase the increment product views use case
	 */
	public IncrementProductViewsController(IncrementProductViewsUseCase incrementProductViewsUseCase) {
		this.incrementProductViewsUseCase = incrementProductViewsUseCase;
	}

	@Override
	public ResponseEntity<Void> incrementProductViews(String id) {
		incrementProductViewsUseCase.execute(
				new IncrementProductViewsCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

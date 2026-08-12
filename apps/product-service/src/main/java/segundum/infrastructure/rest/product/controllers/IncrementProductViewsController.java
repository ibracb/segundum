package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.IncrementProductViewsCommand;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.facades.IncrementProductViewsFacade;
import segundum.infrastructure.rest.product.api.IncrementProductViewsApi;

/**
 * Represents the controller for incrementing the views of a product.
 */
@RestController
public class IncrementProductViewsController implements IncrementProductViewsApi {

	/**
	 * The facade for incrementing product views.
	 */
	private final IncrementProductViewsFacade facade;

	/**
	 * Constructs a new IncrementProductViewsController with the given facade.
	 *
	 * @param facade the facade for incrementing product views
	 */
	public IncrementProductViewsController(IncrementProductViewsFacade facade) {
		this.facade = facade;
	}

	@Override
	public ResponseEntity<Void> incrementProductViews(String id) {
		facade.run(new IncrementProductViewsCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

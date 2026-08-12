package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.DiscardProductCommand;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.facades.DiscardProductFacade;
import segundum.infrastructure.rest.product.api.DiscardProductApi;

/**
 * Represents the controller for discarding a draft product.
 */
@RestController
public class DiscardProductController implements DiscardProductApi {

	/**
	 * The facade for discarding a product.
	 */
	private final DiscardProductFacade facade;

	/**
	 * Constructs a new DiscardProductController with the given facade.
	 *
	 * @param facade the facade for discarding a product
	 */
	public DiscardProductController(DiscardProductFacade facade) {
		this.facade = facade;
	}

	@Override
	public ResponseEntity<Void> discardProduct(String id) {
		facade.run(new DiscardProductCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

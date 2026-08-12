package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.RemoveProductCommand;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.facades.RemoveProductFacade;
import segundum.infrastructure.rest.product.api.RemoveProductApi;

/**
 * Represents the controller for removing a product.
 */
@RestController
public class RemoveProductController implements RemoveProductApi {

	/**
	 * The facade for removing a product.
	 */
	private final RemoveProductFacade facade;

	/**
	 * Constructs a new RemoveProductController with the given facade.
	 *
	 * @param facade the facade for removing a product
	 */
	public RemoveProductController(RemoveProductFacade facade) {
		this.facade = facade;
	}

	@Override
	public ResponseEntity<Void> removeProduct(String id) {
		facade.run(new RemoveProductCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.TakeProductDownCommand;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.facades.TakeProductDownFacade;
import segundum.infrastructure.rest.product.api.TakeProductDownApi;

/**
 * Represents the controller for taking a product down from sale.
 */
@RestController
public class TakeProductDownController implements TakeProductDownApi {

	/**
	 * The facade for taking a product down from sale.
	 */
	private final TakeProductDownFacade facade;

	/**
	 * Constructs a new TakeProductDownController with the given facade.
	 *
	 * @param facade the facade for taking a product down from sale
	 */
	public TakeProductDownController(TakeProductDownFacade facade) {
		this.facade = facade;
	}

	@Override
	public ResponseEntity<Void> takeProductDown(String id) {
		facade.run(new TakeProductDownCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

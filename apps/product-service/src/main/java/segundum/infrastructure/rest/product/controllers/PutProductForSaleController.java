package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.PutProductForSaleCommand;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.facades.PutProductForSaleFacade;
import segundum.infrastructure.rest.product.api.PutProductForSaleApi;

/**
 * Represents the controller for putting a product for sale.
 */
@RestController
public class PutProductForSaleController implements PutProductForSaleApi {

	/**
	 * The facade for putting a product for sale.
	 */
	private final PutProductForSaleFacade facade;

	/**
	 * Constructs a new PutProductForSaleController with the given facade.
	 *
	 * @param facade the facade for putting a product for sale
	 */
	public PutProductForSaleController(PutProductForSaleFacade facade) {
		this.facade = facade;
	}

	@Override
	public ResponseEntity<Void> putProductForSale(String id) {
		facade.run(new PutProductForSaleCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

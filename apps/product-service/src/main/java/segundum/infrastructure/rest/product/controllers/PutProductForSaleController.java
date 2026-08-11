package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.PutProductForSaleCommand;
import segundum.application.usecases.PutProductForSaleUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.PutProductForSaleApi;

/**
 * Represents the controller for putting a product for sale.
 */
@RestController
public class PutProductForSaleController implements PutProductForSaleApi {

	/**
	 * The put product on sale use case.
	 */
	private final PutProductForSaleUseCase putProductOnSaleUseCase;

	/**
	 * Constructs a new PutProductForSaleController with the given dependencies.
	 *
	 * @param putProductOnSaleUseCase the put product on sale use case
	 */
	public PutProductForSaleController(PutProductForSaleUseCase putProductOnSaleUseCase) {
		this.putProductOnSaleUseCase = putProductOnSaleUseCase;
	}

	@Override
	public ResponseEntity<Void> putProductForSale(String id) {
		putProductOnSaleUseCase.execute(new PutProductForSaleCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

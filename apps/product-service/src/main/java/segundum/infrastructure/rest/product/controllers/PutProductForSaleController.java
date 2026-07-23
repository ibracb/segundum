package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.PutProductForSaleCommand;
import segundum.application.usecases.PutProductForSaleUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.PutProductForSaleApi;

@RestController
public class PutProductForSaleController implements PutProductForSaleApi {

	private final PutProductForSaleUseCase putProductOnSaleUseCase;

	public PutProductForSaleController(PutProductForSaleUseCase putProductOnSaleUseCase) {
		this.putProductOnSaleUseCase = putProductOnSaleUseCase;
	}

	@Override
	public ResponseEntity<Void> putProductForSale(String id) {
		putProductOnSaleUseCase.execute(new PutProductForSaleCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

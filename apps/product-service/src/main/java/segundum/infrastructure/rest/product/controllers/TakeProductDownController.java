package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.TakeProductDownCommand;
import segundum.application.usecases.TakeProductDownUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.TakeProductDownApi;

@RestController
public class TakeProductDownController implements TakeProductDownApi {

	private final TakeProductDownUseCase takeProductDownUseCase;

	public TakeProductDownController(TakeProductDownUseCase takeProductDownUseCase) {
		this.takeProductDownUseCase = takeProductDownUseCase;
	}

	@Override
	public ResponseEntity<Void> takeProductDown(String id) {
		takeProductDownUseCase.execute(new TakeProductDownCommand(ProductId.fromString(id)));
		return ResponseEntity.noContent().build();
	}

}

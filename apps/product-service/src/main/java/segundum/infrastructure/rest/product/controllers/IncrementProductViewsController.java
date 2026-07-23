package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.IncrementProductViewsCommand;
import segundum.application.usecases.IncrementProductViewsUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.IncrementProductViewsApi;

@RestController
public class IncrementProductViewsController implements IncrementProductViewsApi {

	private final IncrementProductViewsUseCase incrementProductViewsUseCase;

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

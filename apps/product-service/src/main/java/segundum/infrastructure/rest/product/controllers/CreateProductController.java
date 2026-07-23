package segundum.infrastructure.rest.product.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import segundum.application.commands.CreateProductCommand;
import segundum.application.usecases.CreateProductUseCase;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.product.Title;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.rest.product.api.CreateProductApi;
import segundum.infrastructure.rest.product.requests.CreateProductRequest;

@RestController
public class CreateProductController implements CreateProductApi {

	private final CreateProductUseCase createProductUseCase;

	public CreateProductController(CreateProductUseCase createProductUseCase) {
		this.createProductUseCase = createProductUseCase;
	}

	@Override
	public ResponseEntity<Void> createProduct(CreateProductRequest request) {
		CreateProductCommand command = new CreateProductCommand(
				new Title(request.getTitle()),
				new Description(request.getDescription()),
				new Price(request.getPrice()),
				ConditionStatus.valueOf(request.getConditionStatus()),
				CategoryId.fromString(request.getCategoryId()),
				request.isShippingAvailable(),
				SellerId.fromString(request.getSellerId()));
		ProductId productId = createProductUseCase.execute(command);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(productId.getValue().toString())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}

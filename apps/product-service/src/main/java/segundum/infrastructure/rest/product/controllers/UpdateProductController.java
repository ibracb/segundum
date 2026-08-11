package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.UpdateProductCommand;
import segundum.application.usecases.UpdateProductUseCase;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Description;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.UpdateProductApi;
import segundum.infrastructure.rest.product.requests.UpdateProductRequest;

/**
 * Represents the controller for updating a product.
 */
@RestController
public class UpdateProductController implements UpdateProductApi {

	/**
	 * The update product use case.
	 */
	private final UpdateProductUseCase updateProductUseCase;

	/**
	 * Constructs a new UpdateProductController with the given dependencies.
	 *
	 * @param updateProductUseCase the update product use case
	 */
	public UpdateProductController(UpdateProductUseCase updateProductUseCase) {
		this.updateProductUseCase = updateProductUseCase;
	}

	@Override
	public ResponseEntity<Void> updateProduct(String id, UpdateProductRequest request) {
		Price price = request.getPrice() != null ? new Price(request.getPrice()) : null;
		Description description = request.getDescription() != null
				? new Description(request.getDescription()) : null;
		ConditionStatus conditionStatus = request.getConditionStatus() != null
				? ConditionStatus.valueOf(request.getConditionStatus()) : null;
		UpdateProductCommand command = new UpdateProductCommand(
				ProductId.fromString(id),
				price,
				description,
				conditionStatus);
		updateProductUseCase.execute(command);
		return ResponseEntity.noContent().build();
	}

}

package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.AssignProductPickupLocationCommand;
import segundum.application.usecases.AssignProductPickupLocationUseCase;
import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.AssignPickupLocationApi;
import segundum.infrastructure.rest.product.requests.AssignPickupLocationRequest;

/**
 * Represents the controller for assigning a pickup location to a product.
 */
@RestController
public class AssignPickupLocationController implements AssignPickupLocationApi {

	/**
	 * The assign product pickup location use case.
	 */
	private final AssignProductPickupLocationUseCase assignProductPickupLocationUseCase;

	/**
	 * Constructs a new AssignPickupLocationController with the given dependencies.
	 *
	 * @param assignProductPickupLocationUseCase the assign product pickup location use case
	 */
	public AssignPickupLocationController(AssignProductPickupLocationUseCase assignProductPickupLocationUseCase) {
		this.assignProductPickupLocationUseCase = assignProductPickupLocationUseCase;
	}

	@Override
	public ResponseEntity<Void> assignPickupLocation(String id, AssignPickupLocationRequest request) {
		PickupLocation pickupLocation = new PickupLocation(
				request.getDescription(),
				request.getLatitude(),
				request.getLongitude());
		AssignProductPickupLocationCommand command = new AssignProductPickupLocationCommand(
				ProductId.fromString(id),
				pickupLocation);
		assignProductPickupLocationUseCase.execute(command);
		return ResponseEntity.noContent().build();
	}

}

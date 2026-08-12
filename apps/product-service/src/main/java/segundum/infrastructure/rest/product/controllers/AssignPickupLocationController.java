package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.AssignProductPickupLocationCommand;
import segundum.domain.models.pickup.PickupLocation;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.facades.AssignPickupLocationFacade;
import segundum.infrastructure.rest.product.api.AssignPickupLocationApi;
import segundum.infrastructure.rest.product.requests.AssignPickupLocationRequest;

/**
 * Represents the controller for assigning a pickup location to a product.
 */
@RestController
public class AssignPickupLocationController implements AssignPickupLocationApi {

	/**
	 * The facade for assigning a pickup location.
	 */
	private final AssignPickupLocationFacade facade;

	/**
	 * Constructs a new AssignPickupLocationController with the given facade.
	 *
	 * @param facade the facade for assigning a pickup location
	 */
	public AssignPickupLocationController(AssignPickupLocationFacade facade) {
		this.facade = facade;
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
		facade.run(command);
		return ResponseEntity.noContent().build();
	}

}

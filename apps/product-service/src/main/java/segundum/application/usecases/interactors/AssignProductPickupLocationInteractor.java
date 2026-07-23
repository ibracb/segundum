package segundum.application.usecases.interactors;

import segundum.application.commands.AssignProductPickupLocationCommand;
import segundum.application.usecases.AssignProductPickupLocationUseCase;
import segundum.domain.events.PickupLocationAssigned;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Represents the interactor for assigning a pickup location to a product.
 */
public class AssignProductPickupLocationInteractor implements AssignProductPickupLocationUseCase {

	private final ProductWriteRepository productWriteRepository;
	private final DomainEventPublisher domainEventPublisher;

	public AssignProductPickupLocationInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(AssignProductPickupLocationCommand command) {
		Product product = productWriteRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));
		product.assignPickupLocation(command.getPickupLocation());

		productWriteRepository.update(product);

		domainEventPublisher.publish(new PickupLocationAssigned(
				command.getProductId(),
				command.getPickupLocation()));
	}

}

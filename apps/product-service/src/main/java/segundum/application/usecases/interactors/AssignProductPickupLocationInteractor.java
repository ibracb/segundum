package segundum.application.usecases.interactors;

import segundum.application.commands.AssignProductPickupLocationCommand;
import segundum.application.usecases.AssignProductPickupLocationUseCase;
import segundum.domain.events.PickupLocationAssigned;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.application.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductRepository;

/**
 * Represents the interactor for assigning a pickup location to a product.
 */
public class AssignProductPickupLocationInteractor implements AssignProductPickupLocationUseCase {

	private final ProductRepository productRepository;
	private final DomainEventPublisher domainEventPublisher;

	public AssignProductPickupLocationInteractor(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productRepository = productRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(AssignProductPickupLocationCommand command) {
		Product product = productRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));
		product.assignPickupLocation(command.getPickupLocation());

		productRepository.update(product);

		domainEventPublisher.publish(new PickupLocationAssigned(
				command.getProductId(),
				command.getPickupLocation()));
	}

}

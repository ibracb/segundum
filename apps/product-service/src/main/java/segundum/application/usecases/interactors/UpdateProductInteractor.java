package segundum.application.usecases.interactors;

import segundum.application.commands.UpdateProductCommand;
import segundum.application.usecases.UpdateProductUseCase;
import segundum.domain.events.ProductUpdated;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductWriteRepository;


/**
 * Represents the interactor for updating an existing product in the system.
 */
public class UpdateProductInteractor implements UpdateProductUseCase {

	private final ProductWriteRepository productWriteRepository;
	private final DomainEventPublisher domainEventPublisher;

	public UpdateProductInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(UpdateProductCommand command) {
		Product product = productWriteRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		if (command.getPrice() != null) {
			product.changePrice(command.getPrice());
		}
		if (command.getDescription() != null) {
			product.changeDescription(command.getDescription());
		}
		if (command.getConditionStatus() != null) {
			product.changeConditionStatus(command.getConditionStatus());
		}

		productWriteRepository.update(product);

		domainEventPublisher.publish(new ProductUpdated(
				command.getProductId(),
				command.getPrice(),
				command.getDescription(),
				command.getConditionStatus()));
	}

}

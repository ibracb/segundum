package segundum.application.usecases.interactors;

import segundum.application.commands.RemoveProductCommand;
import segundum.application.usecases.RemoveProductUseCase;
import segundum.domain.events.ProductRemoved;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Interactor for permanently removing a product that is for sale.
 */
public class RemoveProductInteractor implements RemoveProductUseCase {

	private final ProductWriteRepository productWriteRepository;
	private final DomainEventPublisher domainEventPublisher;

	public RemoveProductInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(RemoveProductCommand command) {
		Product product = productWriteRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		product.remove();

		productWriteRepository.update(product);

		domainEventPublisher.publish(new ProductRemoved(product.getProductId()));
	}

}

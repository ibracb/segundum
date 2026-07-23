package segundum.application.usecases.interactors;

import segundum.application.commands.DiscardProductCommand;
import segundum.application.usecases.DiscardProductUseCase;
import segundum.domain.events.ProductDiscarded;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Interactor for discarding a draft product.
 */
public class DiscardProductInteractor implements DiscardProductUseCase {

	private final ProductWriteRepository productWriteRepository;
	private final DomainEventPublisher domainEventPublisher;

	public DiscardProductInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(DiscardProductCommand command) {
		Product product = productWriteRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		product.discard();

		productWriteRepository.update(product);

		domainEventPublisher.publish(new ProductDiscarded(product.getProductId()));
	}

}

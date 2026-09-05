package segundum.application.usecases.interactors;

import segundum.application.commands.DiscardProductCommand;
import segundum.application.usecases.DiscardProductUseCase;
import segundum.domain.events.ProductDiscarded;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.application.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductRepository;

/**
 * Interactor for discarding a draft product.
 */
public class DiscardProductInteractor implements DiscardProductUseCase {

	private final ProductRepository productRepository;
	private final DomainEventPublisher domainEventPublisher;

	public DiscardProductInteractor(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productRepository = productRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(DiscardProductCommand command) {
		Product product = productRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		product.discard();

		productRepository.update(product);

		domainEventPublisher.publish(new ProductDiscarded(product.getProductId()));
	}

}

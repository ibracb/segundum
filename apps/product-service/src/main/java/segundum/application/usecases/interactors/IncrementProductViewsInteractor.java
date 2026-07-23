package segundum.application.usecases.interactors;

import segundum.application.commands.IncrementProductViewsCommand;
import segundum.application.usecases.IncrementProductViewsUseCase;
import segundum.domain.events.ProductViewsIncremented;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Represents the interactor for incrementing the views of a product.
 */
public class IncrementProductViewsInteractor implements IncrementProductViewsUseCase {

	private final ProductWriteRepository productWriteRepository;
	private final DomainEventPublisher domainEventPublisher;

	public IncrementProductViewsInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(IncrementProductViewsCommand command) {
		Product product = productWriteRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));
		product.incrementViews();

		productWriteRepository.update(product);

		domainEventPublisher.publish(new ProductViewsIncremented(
				command.getProductId()));
	}

}

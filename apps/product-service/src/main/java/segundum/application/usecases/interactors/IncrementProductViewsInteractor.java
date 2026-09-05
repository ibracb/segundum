package segundum.application.usecases.interactors;

import segundum.application.commands.IncrementProductViewsCommand;
import segundum.application.usecases.IncrementProductViewsUseCase;
import segundum.domain.events.ProductViewsIncremented;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.application.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductRepository;

/**
 * Represents the interactor for incrementing the views of a product.
 */
public class IncrementProductViewsInteractor implements IncrementProductViewsUseCase {

	private final ProductRepository productRepository;
	private final DomainEventPublisher domainEventPublisher;

	public IncrementProductViewsInteractor(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productRepository = productRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(IncrementProductViewsCommand command) {
		Product product = productRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));
		product.incrementViews();

		productRepository.update(product);

		domainEventPublisher.publish(new ProductViewsIncremented(
				command.getProductId()));
	}

}

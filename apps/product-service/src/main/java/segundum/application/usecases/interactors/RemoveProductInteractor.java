package segundum.application.usecases.interactors;

import segundum.application.commands.RemoveProductCommand;
import segundum.application.usecases.RemoveProductUseCase;
import segundum.domain.events.ProductRemoved;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.application.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductRepository;

/**
 * Interactor for permanently removing a product that is for sale.
 */
public class RemoveProductInteractor implements RemoveProductUseCase {

	private final ProductRepository productRepository;
	private final DomainEventPublisher domainEventPublisher;

	public RemoveProductInteractor(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productRepository = productRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(RemoveProductCommand command) {
		Product product = productRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		product.remove();

		productRepository.update(product);

		domainEventPublisher.publish(new ProductRemoved(product.getProductId()));
	}

}

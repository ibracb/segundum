package segundum.application.usecases.interactors;

import segundum.application.commands.TakeProductDownCommand;
import segundum.application.usecases.TakeProductDownUseCase;
import segundum.domain.events.ProductTakenDown;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.application.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductRepository;

/**
 * Interactor for taking a product down from sale.
 */
public class TakeProductDownInteractor implements TakeProductDownUseCase {

	private final ProductRepository productRepository;
	private final DomainEventPublisher domainEventPublisher;

	public TakeProductDownInteractor(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productRepository = productRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(TakeProductDownCommand command) {
		Product product = productRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		product.takeDown();

		productRepository.update(product);

		domainEventPublisher.publish(new ProductTakenDown(product.getProductId()));
	}

}

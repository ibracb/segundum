package segundum.application.usecases.interactors;

import segundum.application.commands.TakeProductDownCommand;
import segundum.application.usecases.TakeProductDownUseCase;
import segundum.domain.events.ProductTakenDown;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Interactor for taking a product down from sale.
 */
public class TakeProductDownInteractor implements TakeProductDownUseCase {

	private final ProductWriteRepository productWriteRepository;
	private final DomainEventPublisher domainEventPublisher;

	public TakeProductDownInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(TakeProductDownCommand command) {
		Product product = productWriteRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		product.takeDown();

		productWriteRepository.update(product);

		domainEventPublisher.publish(new ProductTakenDown(product.getProductId()));
	}

}

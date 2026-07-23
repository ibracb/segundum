package segundum.application.usecases.interactors;

import segundum.application.commands.PutProductForSaleCommand;
import segundum.application.usecases.PutProductForSaleUseCase;
import segundum.domain.events.ProductPutOnSale;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Interactor for putting a product on sale.
 */
public class PutProductForSaleInteractor implements PutProductForSaleUseCase {

	private final ProductWriteRepository productWriteRepository;
	private final DomainEventPublisher domainEventPublisher;

	public PutProductForSaleInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(PutProductForSaleCommand command) {
		Product product = productWriteRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		product.putForSale();

		productWriteRepository.update(product);

		domainEventPublisher.publish(new ProductPutOnSale(product.getProductId()));
	}

}

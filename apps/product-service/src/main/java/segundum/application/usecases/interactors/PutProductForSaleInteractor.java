package segundum.application.usecases.interactors;

import segundum.application.commands.PutProductForSaleCommand;
import segundum.application.usecases.PutProductForSaleUseCase;
import segundum.domain.events.ProductPutOnSale;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.Product;
import segundum.application.outbound.DomainEventPublisher;
import segundum.domain.repositories.ProductRepository;

/**
 * Interactor for putting a product on sale.
 */
public class PutProductForSaleInteractor implements PutProductForSaleUseCase {

	private final ProductRepository productRepository;
	private final DomainEventPublisher domainEventPublisher;

	public PutProductForSaleInteractor(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		this.productRepository = productRepository;
		this.domainEventPublisher = domainEventPublisher;
	}

	@Override
	public void execute(PutProductForSaleCommand command) {
		Product product = productRepository.findById(command.getProductId())
				.orElseThrow(() -> new EntityNotFoundException("Product", command.getProductId().getValue().toString()));

		product.putForSale();

		productRepository.update(product);

		domainEventPublisher.publish(new ProductPutOnSale(product.getProductId()));
	}

}

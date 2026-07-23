package segundum.application.eventhandlers.sales.interactors;

import java.util.Optional;

import segundum.application.eventhandlers.sales.SaleCompletedHandler;
import segundum.application.events.sales.SaleCompleted;
import segundum.domain.events.ProductSold;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Interactor for handling sale completed events.
 * Updates the product sale status to SOLD and publishes a ProductSold domain event.
 */
public class SaleCompletedInteractor implements SaleCompletedHandler {

	/**
	 * The repository for writing product data.
	 */
	private final ProductWriteRepository productWriteRepository;
	
	/**
	 * The publisher for domain events.
	 */
	private final DomainEventPublisher domainEventPublisher;
	
	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * Constructs a new SaleCompletedHandlerInteractor with the given dependencies.
	 *
	 * @param productWriteRepository the repository for writing product data
	 * @param domainEventPublisher the publisher for domain events
	 * @param logger the logger
	 */
	public SaleCompletedInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher, LogEmitter logEmitter) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.logEmitter = logEmitter;
	}

	@Override
	public void handle(SaleCompleted event) {
		try {
			ProductId productId = ProductId.fromUUID(event.getProductId());
			Optional<Product> product = productWriteRepository.findById(productId);
			if (product.isEmpty()) {
				logEmitter.warn("Product with ID " + productId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Product productEntity = product.get();
			productEntity.sell();
			productWriteRepository.update(productEntity);
			domainEventPublisher.publish(new ProductSold(productId));
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

}

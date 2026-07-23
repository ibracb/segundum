package segundum.application.eventhandlers.sales.interactors;

import java.util.Optional;

import segundum.application.eventhandlers.sales.SaleCancelledHandler;
import segundum.application.events.sales.SaleCancelled;
import segundum.domain.events.ProductReservationCancelled;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Interactor for handling reservation cancelled events.
 * Updates the product sale status back to FOR_SALE and publishes a ProductReservationCancelled domain event.
 */
public class SaleCancelledInteractor implements SaleCancelledHandler {

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
	 * Constructs a new ReservationCancelledHandlerInteractor with the given dependencies.
	 *
	 * @param productWriteRepository the repository for writing product data
	 * @param domainEventPublisher the publisher for domain events
	 * @param logger the logger
	 */
	public SaleCancelledInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher, LogEmitter logEmitter) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.logEmitter = logEmitter;
	}

	@Override
	public void handle(SaleCancelled event) {
		try {
			ProductId productId = ProductId.fromUUID(event.getProductId());
			Optional<Product> product = productWriteRepository.findById(productId);
			if (product.isEmpty()) {
				logEmitter.warn("Product with ID " + productId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Product productEntity = product.get();
			productEntity.cancelReservation();
			productWriteRepository.update(productEntity);
			domainEventPublisher.publish(new ProductReservationCancelled(productId));
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

}

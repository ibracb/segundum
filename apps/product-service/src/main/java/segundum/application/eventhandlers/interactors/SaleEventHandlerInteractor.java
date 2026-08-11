package segundum.application.eventhandlers.interactors;

import java.util.Optional;

import segundum.application.eventhandlers.SaleEventHandler;
import segundum.application.events.sales.SaleCancelled;
import segundum.application.events.sales.SaleCompleted;
import segundum.application.events.sales.SaleReserved;
import segundum.domain.events.ProductReservationCancelled;
import segundum.domain.events.ProductReserved;
import segundum.domain.events.ProductSold;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * Interactor for handling events from the sales bounded context.
 * Updates the product sale status accordingly and publishes the corresponding domain event.
 */
public class SaleEventHandlerInteractor implements SaleEventHandler {

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
	 * Constructs a new SalesHandlerInteractor with the given dependencies.
	 *
	 * @param productWriteRepository the repository for writing product data
	 * @param domainEventPublisher the publisher for domain events
	 * @param logEmitter the logger
	 */
	public SaleEventHandlerInteractor(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher, LogEmitter logEmitter) {
		this.productWriteRepository = productWriteRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.logEmitter = logEmitter;
	}

	@Override
	public void onSaleReserved(SaleReserved event) {
		try {
			ProductId productId = ProductId.fromUUID(event.getProductId());
			Optional<Product> product = productWriteRepository.findById(productId);
			if (product.isEmpty()) {
				logEmitter.warn("Product with ID " + productId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Product productEntity = product.get();
			productEntity.reserve();
			productWriteRepository.update(productEntity);
			domainEventPublisher.publish(new ProductReserved(productId));
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

	@Override
	public void onSaleCompleted(SaleCompleted event) {
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

	@Override
	public void onSaleCancelled(SaleCancelled event) {
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

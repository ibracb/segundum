package segundum.application.notificationhandlers.interactors;

import java.util.Optional;

import segundum.application.notificationhandlers.SaleNotificationHandler;
import segundum.application.notifications.sales.SaleCancelledNotification;
import segundum.application.notifications.sales.SaleCompletedNotification;
import segundum.application.notifications.sales.SaleReservedNotification;
import segundum.domain.events.ProductReservationCancelled;
import segundum.domain.events.ProductReserved;
import segundum.domain.events.ProductSold;
import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;
import segundum.application.outbound.DomainEventPublisher;
import segundum.application.outbound.LogEmitter;
import segundum.domain.repositories.ProductRepository;
import segundum.domain.exceptions.DomainException;

/**
 * Interactor for handling notifications from the sales bounded context.
 * Updates product sale status and publishes domain events.
 */
public class SaleNotificationHandlerInteractor implements SaleNotificationHandler {

	private final ProductRepository productRepository;
	private final DomainEventPublisher domainEventPublisher;
	private final LogEmitter logEmitter;

	/**
	 * Constructs a new SaleNotificationHandlerInteractor with the given dependencies.
	 *
	 * @param productRepository the repository for managing product data
	 * @param domainEventPublisher the publisher for domain events
	 * @param logEmitter the logger
	 */
	public SaleNotificationHandlerInteractor(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher, LogEmitter logEmitter) {
		this.productRepository = productRepository;
		this.domainEventPublisher = domainEventPublisher;
		this.logEmitter = logEmitter;
	}

	@Override
	public void onSaleReservedNotification(SaleReservedNotification event) {
		try {
			ProductId productId = ProductId.fromUUID(event.getProductId());
			Optional<Product> product = productRepository.findById(productId);
			if (product.isEmpty()) {
				logEmitter.warn("Product with ID " + productId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Product productEntity = product.get();
			productEntity.reserve();
			productRepository.update(productEntity);
			domainEventPublisher.publish(new ProductReserved(productId));
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
		catch(Exception e) {
			logEmitter.error("Unexpected error processing " + event.getClass().getSimpleName() + ": " + e.getMessage(), e);
		}
	}

	@Override
	public void onSaleCompletedNotification(SaleCompletedNotification event) {
		try {
			ProductId productId = ProductId.fromUUID(event.getProductId());
			Optional<Product> product = productRepository.findById(productId);
			if (product.isEmpty()) {
				logEmitter.warn("Product with ID " + productId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Product productEntity = product.get();
			productEntity.sell();
			productRepository.update(productEntity);
			domainEventPublisher.publish(new ProductSold(productId));
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
		catch(Exception e) {
			logEmitter.error("Unexpected error processing " + event.getClass().getSimpleName() + ": " + e.getMessage(), e);
		}
	}

	@Override
	public void onSaleCancelledNotification(SaleCancelledNotification event) {
		try {
			ProductId productId = ProductId.fromUUID(event.getProductId());
			Optional<Product> product = productRepository.findById(productId);
			if (product.isEmpty()) {
				logEmitter.warn("Product with ID " + productId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Product productEntity = product.get();
			productEntity.cancelReservation();
			productRepository.update(productEntity);
			domainEventPublisher.publish(new ProductReservationCancelled(productId));
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
		catch(Exception e) {
			logEmitter.error("Unexpected error processing " + event.getClass().getSimpleName() + ": " + e.getMessage(), e);
		}
	}

}

package segundum.infrastructure.messaging.messages;

import org.springframework.stereotype.Component;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.PickupLocationAssigned;
import segundum.domain.events.ProductCreated;
import segundum.domain.events.ProductDiscarded;
import segundum.domain.events.ProductPutOnSale;
import segundum.domain.events.ProductRemoved;
import segundum.domain.events.ProductReservationCancelled;
import segundum.domain.events.ProductReserved;
import segundum.domain.events.ProductSold;
import segundum.domain.events.ProductTakenDown;
import segundum.domain.events.ProductUpdated;
import segundum.domain.events.ProductViewsIncremented;

/**
 * Maps product domain events to their raw message representations.
 */
@Component
public class ProductMessageMapper {

	public Object map(DomainEvent event) {
		if (event instanceof ProductCreated) return map((ProductCreated) event);
		else if (event instanceof ProductUpdated) return map((ProductUpdated) event);
		else if (event instanceof ProductPutOnSale) return map((ProductPutOnSale) event);
		else if (event instanceof ProductTakenDown) return map((ProductTakenDown) event);
		else if (event instanceof ProductReserved) return map((ProductReserved) event);
		else if (event instanceof ProductSold) return map((ProductSold) event);
		else if (event instanceof ProductReservationCancelled) return map((ProductReservationCancelled) event);
		else if (event instanceof ProductRemoved) return map((ProductRemoved) event);
		else if (event instanceof ProductDiscarded) return map((ProductDiscarded) event);
		else if (event instanceof ProductViewsIncremented) return map((ProductViewsIncremented) event);
		else if (event instanceof PickupLocationAssigned) return map((PickupLocationAssigned) event);
		else throw new IllegalArgumentException("Unsupported event type: " + event.getClass().getSimpleName());
	}

	private String eventId(DomainEvent event) {
		return event.getEventId().toString();
	}

	private String type(DomainEvent event) {
		return event.getType();
	}

	private String timestamp(DomainEvent event) {
		return event.getTimestamp().toString();
	}

	private String productId(ProductCreated event) {
		return event.getProductId().getValue().toString();
	}

	private ProductCreatedMessage map(ProductCreated event) {
		return new ProductCreatedMessage(
				eventId(event),
				type(event),
				timestamp(event),
				productId(event),
				event.getTitle().getValue(),
				event.getDescription().getValue(),
				event.getPrice().getValue(),
				event.getPublicationDate().getValue().toString(),
				event.getConditionStatus().name(),
				event.getCategoryId().getValue(),
				event.isShippingAvailable(),
				event.getSellerId().getValue().toString());
	}

	private ProductUpdatedMessage map(ProductUpdated event) {
		return new ProductUpdatedMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString(),
				event.getPrice().getValue(),
				event.getDescription().getValue(),
				event.getConditionStatus().name());
	}

	private ProductPutOnSaleMessage map(ProductPutOnSale event) {
		return new ProductPutOnSaleMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString());
	}

	private ProductTakenDownMessage map(ProductTakenDown event) {
		return new ProductTakenDownMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString());
	}

	private ProductReservedMessage map(ProductReserved event) {
		return new ProductReservedMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString());
	}

	private ProductSoldMessage map(ProductSold event) {
		return new ProductSoldMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString());
	}

	private ProductReservationCancelledMessage map(ProductReservationCancelled event) {
		return new ProductReservationCancelledMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString());
	}

	private ProductRemovedMessage map(ProductRemoved event) {
		return new ProductRemovedMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString());
	}

	private ProductDiscardedMessage map(ProductDiscarded event) {
		return new ProductDiscardedMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString());
	}

	private ProductViewsIncrementedMessage map(ProductViewsIncremented event) {
		return new ProductViewsIncrementedMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString());
	}

	private PickupLocationAssignedMessage map(PickupLocationAssigned event) {
		PickupLocationAssignedMessage.PickupLocationMessage pickupLocation = new PickupLocationAssignedMessage.PickupLocationMessage(
				event.getPickupLocation().getDescription(),
				event.getPickupLocation().getLatitude(),
				event.getPickupLocation().getLongitude());
		return new PickupLocationAssignedMessage(
				eventId(event),
				type(event),
				timestamp(event),
				event.getProductId().getValue().toString(),
				pickupLocation);
	}

}

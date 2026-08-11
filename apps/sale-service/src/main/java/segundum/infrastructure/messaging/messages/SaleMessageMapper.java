package segundum.infrastructure.messaging.messages;

import org.springframework.stereotype.Component;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleCancelledByPurchaser;
import segundum.domain.events.SaleCancelledBySeller;
import segundum.domain.events.SaleCompleted;
import segundum.domain.events.SaleEvent;
import segundum.domain.events.SaleRejected;
import segundum.domain.events.SaleProposed;
import segundum.domain.events.SaleReserved;

/**
 * Maps sale domain events to their raw message representations.
 */
@Component
public class SaleMessageMapper {

	/**
	 * Maps the given sale domain event to its raw message.
	 *
	 * @param event the sale domain event
	 * @return the raw message
	 * @throws IllegalArgumentException if the event type is not supported
	 */
	public Object map(DomainEvent event) {
		if (event instanceof SaleReserved) return map((SaleReserved) event);
		else if (event instanceof SaleCompleted) return map((SaleCompleted) event);
		else if (event instanceof SaleRejected) return map((SaleRejected) event);
		else if (event instanceof SaleCancelledByPurchaser) return map((SaleCancelledByPurchaser) event);
		else if (event instanceof SaleCancelledBySeller) return map((SaleCancelledBySeller) event);
		else if (event instanceof SaleProposed) return map((SaleProposed) event);
		else throw new IllegalArgumentException("Unsupported event type: " + event.getClass().getSimpleName());
	}

	/**
	 * Maps a reservation created event to its raw message.
	 *
	 * @param event the reservation created event
	 * @return the reservation created message
	 */
	private SaleReservedMessage map(SaleReserved event) {
		return new SaleReservedMessage(
				eventId(event),
				event.getType(),
				timestamp(event),
				saleId(event),
				productId(event));
	}

	/**
	 * Maps a sale completed event to its raw message.
	 *
	 * @param event the sale completed event
	 * @return the sale completed message
	 */
	private SaleCompletedMessage map(SaleCompleted event) {
		return new SaleCompletedMessage(
				eventId(event),
				event.getType(),
				timestamp(event),
				saleId(event),
				productId(event),
				event.getSellerId().getValue().toString(),
				event.getPurchaserId().getValue().toString());
	}

	/**
	 * Maps a sale rejected event to its raw message.
	 *
	 * @param event the sale rejected event
	 * @return the sale rejected message
	 */
	private SaleRejectedMessage map(SaleRejected event) {
		return new SaleRejectedMessage(
				eventId(event),
				event.getType(),
				timestamp(event),
				saleId(event),
				productId(event));
	}

	/**
	 * Maps a sale cancelled by the purchaser event to its raw message.
	 *
	 * @param event the sale cancelled by the purchaser event
	 * @return the sale cancelled by the purchaser message
	 */
	private SaleCancelledByPurchaserMessage map(SaleCancelledByPurchaser event) {
		return new SaleCancelledByPurchaserMessage(
				eventId(event),
				event.getType(),
				timestamp(event),
				saleId(event),
				productId(event));
	}

	/**
	 * Maps a sale cancelled by the seller event to its raw message.
	 *
	 * @param event the sale cancelled by the seller event
	 * @return the sale cancelled by the seller message
	 */
	private SaleCancelledBySellerMessage map(SaleCancelledBySeller event) {
		return new SaleCancelledBySellerMessage(
				eventId(event),
				event.getType(),
				timestamp(event),
				saleId(event),
				productId(event));
	}

	/**
	 * Maps a sale proposed event to its raw message.
	 *
	 * @param event the sale proposed event
	 * @return the sale proposed message
	 */
	private SaleProposedMessage map(SaleProposed event) {
		SaleProposedMessage.PickupLocation pickupLocation = null;
		if (event.getPickupLocation() != null) {
			pickupLocation = new SaleProposedMessage.PickupLocation(
					event.getPickupLocation().getDescription(),
					event.getPickupLocation().getLatitude(),
					event.getPickupLocation().getLongitude());
		}
		return new SaleProposedMessage(
				eventId(event),
				event.getType(),
				timestamp(event),
				saleId(event),
				productId(event),
				event.getSellerId().getValue().toString(),
				event.getSellerName().getValue(),
				event.getSellerSurname().getValue(),
				event.getPurchaserId().getValue().toString(),
				event.getPurchaserName().getValue(),
				event.getPurchaserSurname().getValue(),
				event.getPrice().getValue(),
				event.getTitle().getValue(),
				pickupLocation,
				event.getDatetime().getValue());
	}

	/**
	 * Returns the raw sale identifier of the given event.
	 *
	 * @param event the sale event
	 * @return the raw sale identifier
	 */
	private String saleId(SaleEvent event) {
		return event.getSaleId().asString();
	}

	/**
	 * Returns the raw event identifier of the given event.
	 *
	 * @param event the domain event
	 * @return the raw event identifier
	 */
	private String eventId(DomainEvent event) {
		return event.getEventId().toString();
	}

	/**
	 * Returns the raw timestamp of the given event.
	 *
	 * @param event the domain event
	 * @return the raw timestamp
	 */
	private String timestamp(DomainEvent event) {
		return event.getTimestamp().toString();
	}

	/**
	 * Returns the raw product identifier of the given event.
	 *
	 * @param event the sale event
	 * @return the raw product identifier
	 */
	private String productId(SaleEvent event) {
		return event.getProductId().getValue().toString();
	}

}

package segundum.domain.events;

import segundum.domain.models.seller.SellerId;

/**
 * Event published when a seller is deactivated in the replica.
 */
public class SellerDeactivated extends DomainEvent {

	/**
	 * The unique identifier of the seller.
	 */
	private final SellerId sellerId;

	/**
	 * Constructs a new SellerDeactivated event with the given parameters.
	 *
	 * @param sellerId the unique identifier of the seller
	 */
	public SellerDeactivated(SellerId sellerId) {
		super();
		this.sellerId = sellerId;
	}

	/**
	 * Returns the unique identifier of the seller.
	 *
	 * @return the unique identifier of the seller
	 */
	public SellerId getSellerId() {
		return sellerId;
	}

}

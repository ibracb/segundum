package segundum.domain.events;

import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;

/**
 * Event published when a seller is updated in the replica.
 */
public class SellerUpdated extends DomainEvent {

	/**
	 * The unique identifier of the seller.
	 */
	private final SellerId sellerId;

	/**
	 * The new name of the seller.
	 */
	private final Name name;

	/**
	 * The new surname of the seller.
	 */
	private final Surname surname;

	/**
	 * Constructs a new SellerUpdated event with the given parameters.
	 *
	 * @param sellerId the unique identifier of the seller
	 * @param name the new name of the seller
	 * @param surname the new surname of the seller
	 */
	public SellerUpdated(SellerId sellerId, Name name, Surname surname) {
		super();
		this.sellerId = sellerId;
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Returns the unique identifier of the seller.
	 *
	 * @return the unique identifier of the seller
	 */
	public SellerId getSellerId() {
		return sellerId;
	}

	/**
	 * Returns the new name of the seller.
	 *
	 * @return the new name of the seller
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Returns the new surname of the seller.
	 *
	 * @return the new surname of the seller
	 */
	public Surname getSurname() {
		return surname;
	}

}

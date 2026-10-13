package segundum.domain.events;

import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;

/**
 * Event published when a seller is created in the replica.
 */
public class SellerCreated extends DomainEvent {

	/**
	 * The unique identifier of the seller.
	 */
	private final SellerId sellerId;

	/**
	 * The name of the seller.
	 */
	private final Name name;

	/**
	 * The surname of the seller.
	 */
	private final Surname surname;

	/**
	 * The email of the seller.
	 */
	private final Email email;

	/**
	 * Constructs a new SellerCreated event with the given parameters.
	 *
	 * @param sellerId the unique identifier of the seller
	 * @param name the name of the seller
	 * @param surname the surname of the seller
	 * @param email the email of the seller
	 */
	public SellerCreated(SellerId sellerId, Name name, Surname surname, Email email) {
		super();
		this.sellerId = sellerId;
		this.name = name;
		this.surname = surname;
		this.email = email;
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
	 * Returns the name of the seller.
	 *
	 * @return the name of the seller
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Returns the surname of the seller.
	 *
	 * @return the surname of the seller
	 */
	public Surname getSurname() {
		return surname;
	}

	/**
	 * Returns the email of the seller.
	 *
	 * @return the email of the seller
	 */
	public Email getEmail() {
		return email;
	}

}

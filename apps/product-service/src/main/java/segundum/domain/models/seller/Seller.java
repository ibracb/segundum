package segundum.domain.models.seller;

import segundum.domain.exceptions.SameValueException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;

/**
 * Represents a seller in the product service.
 */
public class Seller {

	/**
	 * The unique identifier of the seller.
	 */
	private final SellerId sellerId;

	/**
	 * The name of the seller.
	 */
	private Name name;

	/**
	 * The surname of the seller.
	 */
	private Surname surname;

	/**
	 * The email of the seller.
	 */
	private final Email email;

	/**
	 * The status of the seller.
	 */
	private SellerStatus status;

	/**
	 * Constructs a new Seller object with the given parameters.
	 *
	 * @param sellerId the unique identifier of the seller
	 * @param name the name of the seller
	 * @param surname the surname of the seller
	 * @param email the email of the seller
	 */
	Seller(SellerId sellerId, Name name, Surname surname, Email email) {
		this.sellerId = sellerId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.status = SellerStatus.ACTIVE;
	}

	/**
	 * Constructs a new Seller object for reconstitution from persistence.
	 *
	 * @param sellerId the unique identifier of the seller
	 * @param name the name of the seller
	 * @param surname the surname of the seller
	 * @param email the email of the seller
	 * @param status the status of the seller
	 */
	Seller(SellerId sellerId, Name name, Surname surname, Email email, SellerStatus status) {
		this.sellerId = sellerId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.status = status;
	}
	
	/**
	 * Changes the name of the seller.
	 * 
	 * @param name the new name of the seller
	 */
	public void changeName(Name name) {
		ensureIsActive();
		ensureNameIsNotTheSame(name);
		this.name = name;
	}
	
	/**
	 * Changes the surname of the seller.
	 * 
	 * @param surname the new surname of the seller
	 */
	public void changeSurname(Surname surname) {
		ensureIsActive();
		ensureSurnameIsNotTheSame(surname);
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

	/**
	 * Returns the status of the seller.
	 *
	 * @return the seller status
	 */
	public SellerStatus getStatus() {
		return status;
	}

	/**
	 * Deactivates the seller by setting status to INACTIVE.
	 */
	public void deactivate() {
		ensureIsActive();
		this.status = SellerStatus.INACTIVE;
	}

	/**
	 * Checks if the seller has been deactivated.
	 *
	 * @return true if the seller is inactive, false otherwise
	 */
	public boolean isActive() {
		return this.status == SellerStatus.ACTIVE;
	}

	private void ensureIsActive() {
		if (this.status != SellerStatus.ACTIVE) {
			throw new SellerNotActiveException(this.sellerId);
		}
	}

	private void ensureNameIsNotTheSame(Name name) {
		if (this.name.equals(name)) {
			throw new SameValueException("name");
		}
	}

	private void ensureSurnameIsNotTheSame(Surname surname) {
		if (this.surname.equals(surname)) {
			throw new SameValueException("surname");
		}
	}

}

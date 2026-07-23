package segundum.domain.models.seller;

/**
 * Factory class for creating Seller objects.
 */
public class SellerFactory {

	/**
	 * Private constructor to prevent instantiation of the SellerFactory class.
	 */
	private SellerFactory() {
	}

	/**
	 * Creates a new Seller object from a domain event.
	 *
	 * @param sellerId the unique identifier of the seller
	 * @param name the name of the seller
	 * @param surname the surname of the seller
	 * @param email the email of the seller
	 * @return a new Seller object
	 */
	public static Seller create(SellerId sellerId, Name name, Surname surname, Email email) {
		return new Seller(sellerId, name, surname, email);
	}

	/**
	 * Reconstitutes a Seller object from persistence.
	 *
	 * @param sellerId the unique identifier of the seller
	 * @param name the name of the seller
	 * @param surname the surname of the seller
	 * @param email the email of the seller
	 * @param status the status of the seller
	 * @return the reconstituted Seller object
	 */
	public static Seller reconstitute(SellerId sellerId, Name name, Surname surname, Email email, SellerStatus status) {
		return new Seller(sellerId, name, surname, email, status);
	}

}

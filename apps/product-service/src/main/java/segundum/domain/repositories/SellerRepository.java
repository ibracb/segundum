package segundum.domain.repositories;

import java.util.Optional;

import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerId;

/**
 * Represents the repository for sellers.
 */
public interface SellerRepository {

	/**
	 * Finds a seller by its identifier.
	 *
	 * @param id the seller identifier
	 * @return the seller, if it exists
	 */
	Optional<Seller> findById(SellerId id);

	/**
	 * Checks whether a seller exists by its identifier.
	 *
	 * @param id the seller identifier
	 * @return true if the seller exists, false otherwise
	 */
	boolean existsById(SellerId id);

	/**
	 * Creates a new seller.
	 *
	 * @param seller the seller to create
	 * @return the created seller
	 */
	Seller create(Seller seller);

	/**
	 * Updates an existing seller.
	 *
	 * @param seller the seller to update
	 * @return the updated seller
	 */
	Seller update(Seller seller);

	/**
	 * Checks whether a seller exists by its email.
	 *
	 * @param email the seller email
	 * @return true if a seller with the given email exists, false otherwise
	 */
	boolean existsByEmail(Email email);

}

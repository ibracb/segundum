package segundum.domain.repositories;

import java.util.Optional;

import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerId;

public interface SellerRepository {

	Optional<Seller> findById(SellerId id);

	boolean existsById(SellerId id);

	Seller create(Seller seller);

	Seller update(Seller seller);

	boolean existsByEmail(Email email);

}

package segundum.infrastructure.persistence.jpa.seller;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerId;
import segundum.domain.repositories.SellerRepository;

/**
 * JPA implementation of the SellerRepository port.
 */
@Repository
public class JpaSellerRepository implements SellerRepository {

	/**
	 * The Spring Data JPA repository for seller entities.
	 */
	private final SellerJpaRepository sellerJpaRepository;

	/**
	 * Constructs a new JpaSellerRepository with the given JPA repository.
	 *
	 * @param sellerJpaRepository the Spring Data JPA repository for seller entities
	 */
	public JpaSellerRepository(SellerJpaRepository sellerJpaRepository) {
		this.sellerJpaRepository = sellerJpaRepository;
	}

	@Override
	public void create(Seller seller) {
		sellerJpaRepository.save(SellerMapper.toEntity(seller));
	}

	@Override
	public void update(Seller seller) {
		sellerJpaRepository.save(SellerMapper.toEntity(seller));
	}
	
	@Override
	public boolean existsById(SellerId id) {
		return sellerJpaRepository.existsById(id.getValue().toString());
	}
	
	@Override
	public boolean existsByEmail(Email email) {
		return sellerJpaRepository.existsByEmail(email.getValue());
	}
	
	@Override
	public Optional<Seller> findById(SellerId id) {
		return sellerJpaRepository.findById(id.getValue().toString())
				.map(SellerMapper::toDomain);
	}

}

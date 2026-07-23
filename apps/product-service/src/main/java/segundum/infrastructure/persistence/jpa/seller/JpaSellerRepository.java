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
	public Optional<Seller> findById(SellerId id) {
		return sellerJpaRepository.findById(id.getValue().toString())
				.map(SellerMapper::toDomain);
	}

	@Override
	public boolean existsById(SellerId id) {
		return sellerJpaRepository.existsById(id.getValue().toString());
	}

	@Override
	public Seller create(Seller seller) {
		SellerJpaEntity entity = sellerJpaRepository.save(
				SellerMapper.toEntity(seller));
		return SellerMapper.toDomain(entity);
	}

	@Override
	public Seller update(Seller seller) {
		SellerJpaEntity entity = sellerJpaRepository.save(
				SellerMapper.toEntity(seller));
		return SellerMapper.toDomain(entity);
	}

	@Override
	public void delete(SellerId id) {
		sellerJpaRepository.findById(id.getValue().toString())
				.ifPresent(entity -> {
					entity.setStatus("DELETED");
					sellerJpaRepository.save(entity);
				});
	}

	@Override
	public boolean existsByEmail(Email email) {
		return sellerJpaRepository.existsByEmail(email.getValue());
	}

}

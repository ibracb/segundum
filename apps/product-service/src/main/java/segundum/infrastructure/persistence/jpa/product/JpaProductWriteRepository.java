package segundum.infrastructure.persistence.jpa.product;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;
import segundum.domain.repositories.ProductWriteRepository;

/**
 * JPA implementation of the ProductWriteRepository port.
 */
@Repository
public class JpaProductWriteRepository implements ProductWriteRepository {

	/**
	 * The Spring Data JPA repository for product entities.
	 */
	private final ProductJpaRepository productJpaRepository;

	/**
	 * Constructs a new JpaProductWriteRepository with the given JPA repository.
	 *
	 * @param productJpaRepository the Spring Data JPA repository for product entities
	 */
	public JpaProductWriteRepository(ProductJpaRepository productJpaRepository) {
		this.productJpaRepository = productJpaRepository;
	}

	@Override
	public Optional<Product> findById(ProductId id) {
		return Optional.ofNullable(productJpaRepository.findActiveById(id.getValue().toString()))
				.map(ProductMapper::toDomain);
	}

	@Override
	public void create(Product product) {
		productJpaRepository.save(ProductMapper.toEntity(product));
	}

	@Override
	public void update(Product product) {
		productJpaRepository.save(ProductMapper.toEntity(product));
	}

}

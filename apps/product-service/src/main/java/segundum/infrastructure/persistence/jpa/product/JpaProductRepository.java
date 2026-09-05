package segundum.infrastructure.persistence.jpa.product;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;
import segundum.domain.repositories.ProductRepository;

/**
 * JPA implementation of the ProductRepository port.
 */
@Repository
public class JpaProductRepository implements ProductRepository {

	/**
	 * The Spring Data JPA repository for product entities.
	 */
	private final ProductJpaRepository productJpaRepository;

	/**
	 * Constructs a new JpaProductRepository with the given JPA repository.
	 *
	 * @param productJpaRepository the Spring Data JPA repository for product entities
	 */
	public JpaProductRepository(ProductJpaRepository productJpaRepository) {
		this.productJpaRepository = productJpaRepository;
	}

	@Override
	public void create(Product product) {
		productJpaRepository.save(ProductMapper.toEntity(product));
	}

	@Override
	public void update(Product product) {
		productJpaRepository.save(ProductMapper.toEntity(product));
	}
	
	@Override
	public Optional<Product> findById(ProductId id) {
		return Optional.ofNullable(productJpaRepository.findActiveById(id.getValue().toString()))
				.map(ProductMapper::toDomain);
	}

}

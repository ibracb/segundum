package segundum.infrastructure.persistence.jpa.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for ProductJpaEntity.
 */
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, String> {

	/**
	 * Finds a product by ID excluding deleted products.
	 *
	 * @param id the product identifier
	 * @return the product entity, or null if not found or deleted
	 */
	@Query("SELECT p FROM ProductJpaEntity p WHERE p.id = :id AND p.saleStatus != 'DELETED'")
	ProductJpaEntity findActiveById(@Param("id") String id);

}

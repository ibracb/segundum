package segundum.infrastructure.persistence.jpa.seller;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for seller entities.
 */
public interface SellerJpaRepository extends JpaRepository<SellerJpaEntity, String> {
	
	boolean existsByEmail(String email);

}

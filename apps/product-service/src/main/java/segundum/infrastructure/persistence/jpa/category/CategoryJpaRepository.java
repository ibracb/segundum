package segundum.infrastructure.persistence.jpa.category;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for category entities.
 */
public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, String> {

}

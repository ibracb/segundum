package segundum.infrastructure.persistence.jpa.category;

import org.springframework.stereotype.Repository;

import segundum.domain.models.category.Category;
import segundum.domain.models.category.CategoryId;
import segundum.domain.repositories.CategoryRepository;

/**
 * JPA implementation of the CategoryRepository port.
 */
@Repository
public class JpaCategoryRepository implements CategoryRepository {
	
	/**
	 * JPA repository for performing CRUD operations on Category entities.
	 */
	private final CategoryJpaRepository categoryJpaRepository;

	/**
	 * Constructs a new JpaCategoryRepository with the specified CategoryJpaRepository.
	 *
	 * @param categoryJpaRepository the JPA repository for performing CRUD operations on Category entities
	 */
	public JpaCategoryRepository(CategoryJpaRepository categoryJpaRepository) {
		this.categoryJpaRepository = categoryJpaRepository;
	}

	@Override
	public void create(Category category) {
		categoryJpaRepository.save(CategoryMapper.toEntity(category));
	}

	@Override
	public void update(Category category) {
		categoryJpaRepository.save(CategoryMapper.toEntity(category));
	}

	@Override
	public boolean existsById(CategoryId id) {
		return categoryJpaRepository.existsById(id.getValue());
	}

}

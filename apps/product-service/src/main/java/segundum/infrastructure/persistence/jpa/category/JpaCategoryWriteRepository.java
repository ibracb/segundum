package segundum.infrastructure.persistence.jpa.category;

import org.springframework.stereotype.Repository;

import segundum.domain.models.category.Category;
import segundum.domain.models.category.CategoryId;
import segundum.domain.repositories.CategoryWriteRepository;

/**
 * JPA implementation of the CategoryWriteRepository port.
 */
@Repository
public class JpaCategoryWriteRepository implements CategoryWriteRepository {
	
	/**
	 * JPA repository for performing CRUD operations on Category entities.
	 */
	private final CategoryJpaRepository categoryJpaRepository;

	/**
	 * Constructs a new JpaCategoryWriteRepository with the specified CategoryJpaRepository.
	 *
	 * @param categoryJpaRepository the JPA repository for performing CRUD operations on Category entities
	 */
	public JpaCategoryWriteRepository(CategoryJpaRepository categoryJpaRepository) {
		this.categoryJpaRepository = categoryJpaRepository;
	}

	@Override
	public Category create(Category category) {
		CategoryJpaEntity entity = categoryJpaRepository.save(
				CategoryMapper.toEntity(category));
		return CategoryMapper.toDomain(entity);
	}

	@Override
	public Category update(Category category) {
		CategoryJpaEntity entity = categoryJpaRepository.save(
				CategoryMapper.toEntity(category));
		return CategoryMapper.toDomain(entity);
	}

	@Override
	public boolean existsById(CategoryId id) {
		return categoryJpaRepository.existsById(id.getValue());
	}

}

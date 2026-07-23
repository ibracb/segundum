package segundum.infrastructure.persistence.jpa.category;

import segundum.domain.models.category.Category;
import segundum.domain.models.category.CategoryFactory;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.category.Description;
import segundum.domain.models.category.Name;
import segundum.domain.models.category.Path;

/**
 * Mapper between domain Category and JPA CategoryJpaEntity.
 */
public class CategoryMapper {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private CategoryMapper() {
	}

	/**
	 * Converts a domain Category to a JPA entity.
	 *
	 * @param category the domain category
	 * @return the JPA entity
	 */
	public static CategoryJpaEntity toEntity(Category category) {
		return new CategoryJpaEntity(
				category.getCategoryId().getValue(),
				category.getName().getValue(),
				category.getPath().getValue(),
				category.getDescription().getValue(),
				category.getParentCategoryId() != null
						? category.getParentCategoryId().getValue()
						: null);
	}

	/**
	 * Converts a JPA entity to a domain Category.
	 *
	 * @param entity the JPA entity
	 * @return the domain category
	 */
	public static Category toDomain(CategoryJpaEntity entity) {
		return CategoryFactory.reconstitute(
				CategoryId.fromString(entity.getId()),
				new Name(entity.getName()),
				new Path(entity.getPath()),
				new Description(entity.getDescription()),
				entity.getParentCategoryId() != null
						? CategoryId.fromString(entity.getParentCategoryId())
						: null);
	}

}

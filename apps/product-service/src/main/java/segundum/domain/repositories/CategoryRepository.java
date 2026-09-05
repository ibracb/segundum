package segundum.domain.repositories;

import segundum.domain.models.category.Category;
import segundum.domain.models.category.CategoryId;

/**
 * Repository interface for write operations on Category entities.
 */
public interface CategoryRepository {

	/**
	 * Creates a new category in the repository.
	 *
	 * @param category the category to create
	 * @return the created category
	 */
	Category create(Category category);

	/**
	 * Updates an existing category in the repository.
	 *
	 * @param category the category to update
	 * @return the updated category
	 */
	Category update(Category category);

	/**
	 * Checks if a category exists in the repository by their identifier.
	 *
	 * @param id the identifier of the category to check
	 * @return true if a category with the given identifier exists, false otherwise
	 */
	boolean existsById(CategoryId id);

}

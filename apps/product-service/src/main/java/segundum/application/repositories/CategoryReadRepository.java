package segundum.application.repositories;

import java.util.List;

import segundum.application.readmodels.category.CategoryReadModel;
import segundum.domain.models.category.CategoryId;

/**
 * Represents the read-side repository for categories.
 */
public interface CategoryReadRepository {

	/**
	 * Finds the root categories.
	 *
	 * @return the list of root categories
	 */
	List<CategoryReadModel> findRootCategories();

	/**
	 * Finds the children categories of a given parent category.
	 *
	 * @param parentId the parent category identifier
	 * @return the list of child categories
	 */
	List<CategoryReadModel> findChildrenByParentCategoryId(CategoryId parentId);

}

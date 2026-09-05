package segundum.application.finders;

import java.util.List;

import segundum.application.readmodels.category.CategoryReadModel;
import segundum.domain.models.category.CategoryId;

/**
 * Finder interface for querying category read models.
 */
public interface CategoryFinder {

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

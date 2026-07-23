package segundum.application.usecases;

import java.util.List;

import segundum.application.queries.GetCategoryChildrenQuery;
import segundum.application.readmodels.category.CategoryReadModel;

/**
 * Represents the use case for retrieving children of a category.
 */
public interface GetCategoryChildrenUseCase {

	/**
	 * Executes the use case to retrieve children of a category.
	 *
	 * @param query the query
	 * @return a list of child categories
	 */
	List<CategoryReadModel> execute(GetCategoryChildrenQuery query);

}

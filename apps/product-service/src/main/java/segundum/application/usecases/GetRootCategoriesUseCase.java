package segundum.application.usecases;

import java.util.List;

import segundum.application.queries.GetRootCategoriesQuery;
import segundum.application.readmodels.category.CategoryReadModel;

/**
 * Represents the use case for retrieving all root categories.
 */
public interface GetRootCategoriesUseCase {

	/**
	 * Executes the use case to retrieve all root categories.
	 *
	 * @param query the query
	 * @return a list of root categories
	 */
	List<CategoryReadModel> execute(GetRootCategoriesQuery query);

}

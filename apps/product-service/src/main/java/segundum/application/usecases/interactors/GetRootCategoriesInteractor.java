package segundum.application.usecases.interactors;

import java.util.List;

import segundum.application.queries.GetRootCategoriesQuery;
import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.finders.CategoryFinder;
import segundum.application.usecases.GetRootCategoriesUseCase;

/**
 * Represents the interactor for retrieving all root categories.
 */
public class GetRootCategoriesInteractor implements GetRootCategoriesUseCase {

	private final CategoryFinder categoryFinder;

	public GetRootCategoriesInteractor(CategoryFinder categoryFinder) {
		this.categoryFinder = categoryFinder;
	}

	@Override
	public List<CategoryReadModel> execute(GetRootCategoriesQuery query) {
		return categoryFinder.findRootCategories();
	}

}

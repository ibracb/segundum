package segundum.application.usecases.interactors;

import java.util.List;

import segundum.application.queries.GetCategoryChildrenQuery;
import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.finders.CategoryFinder;
import segundum.application.usecases.GetCategoryChildrenUseCase;

/**
 * Represents the interactor for retrieving children of a category.
 */
public class GetCategoryChildrenInteractor implements GetCategoryChildrenUseCase {

	private final CategoryFinder categoryFinder;

	public GetCategoryChildrenInteractor(CategoryFinder categoryFinder) {
		this.categoryFinder = categoryFinder;
	}

	@Override
	public List<CategoryReadModel> execute(GetCategoryChildrenQuery query) {
		return categoryFinder.findChildrenByParentCategoryId(query.getCategoryId());
	}

}

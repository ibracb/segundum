package segundum.application.usecases.interactors;

import java.util.List;

import segundum.application.queries.GetRootCategoriesQuery;
import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.repositories.CategoryReadRepository;
import segundum.application.usecases.GetRootCategoriesUseCase;

/**
 * Represents the interactor for retrieving all root categories.
 */
public class GetRootCategoriesInteractor implements GetRootCategoriesUseCase {

	private final CategoryReadRepository categoryReadRepository;

	public GetRootCategoriesInteractor(CategoryReadRepository categoryReadRepository) {
		this.categoryReadRepository = categoryReadRepository;
	}

	@Override
	public List<CategoryReadModel> execute(GetRootCategoriesQuery query) {
		return categoryReadRepository.findRootCategories();
	}

}

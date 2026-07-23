package segundum.application.usecases.interactors;

import java.util.List;

import segundum.application.queries.GetCategoryChildrenQuery;
import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.repositories.CategoryReadRepository;
import segundum.application.usecases.GetCategoryChildrenUseCase;

/**
 * Represents the interactor for retrieving children of a category.
 */
public class GetCategoryChildrenInteractor implements GetCategoryChildrenUseCase {

	private final CategoryReadRepository categoryReadRepository;

	public GetCategoryChildrenInteractor(CategoryReadRepository categoryReadRepository) {
		this.categoryReadRepository = categoryReadRepository;
	}

	@Override
	public List<CategoryReadModel> execute(GetCategoryChildrenQuery query) {
		return categoryReadRepository.findChildrenByParentCategoryId(query.getCategoryId());
	}

}

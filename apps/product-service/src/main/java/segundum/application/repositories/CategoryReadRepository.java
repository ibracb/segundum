package segundum.application.repositories;

import java.util.List;

import segundum.application.readmodels.category.CategoryReadModel;
import segundum.domain.models.category.CategoryId;

public interface CategoryReadRepository {

	List<CategoryReadModel> findRootCategories();

	List<CategoryReadModel> findChildrenByParentCategoryId(CategoryId parentId);

}

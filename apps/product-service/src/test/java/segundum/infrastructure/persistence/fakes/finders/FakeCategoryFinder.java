package segundum.infrastructure.persistence.fakes.finders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.finders.CategoryFinder;
import segundum.domain.models.category.CategoryId;

public class FakeCategoryFinder implements CategoryFinder {

	private List<CategoryReadModel> rootCategories = Collections.emptyList();
	private final Map<CategoryId, List<CategoryReadModel>> children = new HashMap<>();

	@Override
	public List<CategoryReadModel> findRootCategories() {
		return rootCategories;
	}

	@Override
	public List<CategoryReadModel> findChildrenByParentCategoryId(CategoryId parentId) {
		return children.getOrDefault(parentId, Collections.emptyList());
	}

	public void setRootCategories(List<CategoryReadModel> rootCategories) {
		this.rootCategories = rootCategories;
	}

	public void setChildren(CategoryId parentId, List<CategoryReadModel> children) {
		this.children.put(parentId, children);
	}
}

package segundum.infrastructure.persistence.fakes.repositories;

import java.util.HashMap;
import java.util.Map;

import segundum.domain.models.category.Category;
import segundum.domain.models.category.CategoryId;
import segundum.domain.repositories.CategoryRepository;

public class FakeCategoryRepository implements CategoryRepository {

	private final Map<CategoryId, Category> categories = new HashMap<>();

	@Override
	public Category create(Category category) {
		categories.put(category.getCategoryId(), category);
		return category;
	}

	@Override
	public Category update(Category category) {
		categories.put(category.getCategoryId(), category);
		return category;
	}

	@Override
	public boolean existsById(CategoryId id) {
		return categories.containsKey(id);
	}

	public void addExistingId(CategoryId id) {
		categories.put(id, null);
	}
}

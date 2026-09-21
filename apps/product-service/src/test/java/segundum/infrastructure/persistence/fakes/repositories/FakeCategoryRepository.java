package segundum.infrastructure.persistence.fakes.repositories;

import java.util.HashMap;
import java.util.Map;

import segundum.domain.models.category.Category;
import segundum.domain.models.category.CategoryId;
import segundum.domain.repositories.CategoryRepository;

public class FakeCategoryRepository implements CategoryRepository {

	private final Map<CategoryId, Category> categories = new HashMap<>();

	@Override
	public void create(Category category) {
		categories.put(category.getCategoryId(), category);
	}

	@Override
	public void update(Category category) {
		categories.put(category.getCategoryId(), category);
	}

	@Override
	public boolean existsById(CategoryId id) {
		return categories.containsKey(id);
	}

	public void addExistingId(CategoryId id) {
		categories.put(id, null);
	}
}

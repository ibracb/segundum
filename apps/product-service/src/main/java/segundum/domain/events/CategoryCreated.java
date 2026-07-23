package segundum.domain.events;

import segundum.domain.models.category.CategoryId;
import segundum.domain.models.category.Description;
import segundum.domain.models.category.Name;
import segundum.domain.models.category.Path;

public class CategoryCreated extends DomainEvent {

	private final CategoryId categoryId;
	private final Name name;
	private final Path path;
	private final Description description;
	private final CategoryId parentCategoryId;

	public CategoryCreated(CategoryId categoryId, Name name, Path path,
			Description description, CategoryId parentCategoryId) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentCategoryId = parentCategoryId;
	}

	public CategoryId getCategoryId() {
		return categoryId;
	}

	public Name getName() {
		return name;
	}

	public Path getPath() {
		return path;
	}

	public Description getDescription() {
		return description;
	}

	public CategoryId getParentCategoryId() {
		return parentCategoryId;
	}

}

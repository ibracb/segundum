package segundum.domain.events;

import segundum.domain.models.category.CategoryId;
import segundum.domain.models.category.Description;
import segundum.domain.models.category.Name;
import segundum.domain.models.category.Path;

/**
 * Represents the domain event emitted when a category is created.
 */
public class CategoryCreated extends DomainEvent {

	/**
	 * The identifier of the created category.
	 */
	private final CategoryId categoryId;
	/**
	 * The name of the created category.
	 */
	private final Name name;
	/**
	 * The path of the created category.
	 */
	private final Path path;
	/**
	 * The description of the created category.
	 */
	private final Description description;
	/**
	 * The identifier of the parent category, if any.
	 */
	private final CategoryId parentCategoryId;

	/**
	 * Constructs a new CategoryCreated event with the given data.
	 *
	 * @param categoryId the category identifier
	 * @param name the category name
	 * @param path the category path
	 * @param description the category description
	 * @param parentCategoryId the parent category identifier
	 */
	public CategoryCreated(CategoryId categoryId, Name name, Path path,
			Description description, CategoryId parentCategoryId) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentCategoryId = parentCategoryId;
	}

	/**
	 * Returns the identifier of the created category.
	 *
	 * @return the category identifier
	 */
	public CategoryId getCategoryId() {
		return categoryId;
	}

	/**
	 * Returns the name of the created category.
	 *
	 * @return the category name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Returns the path of the created category.
	 *
	 * @return the category path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Returns the description of the created category.
	 *
	 * @return the category description
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Returns the identifier of the parent category, if any.
	 *
	 * @return the parent category identifier
	 */
	public CategoryId getParentCategoryId() {
		return parentCategoryId;
	}

}

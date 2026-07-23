package segundum.domain.models.category;

/**
 * Represents a category in the product service.
 */
public class Category {

	/**
	 * The unique identifier of the category.
	 */
	private final CategoryId categoryId;

	/**
	 * The name of the category.
	 */
	private final Name name;

	/**
	 * The path of the category in the hierarchy (e.g., |222|3895|).
	 */
	private final Path path;

	/**
	 * The description of the category (nullable).
	 */
	private final Description description;

	/**
	 * The unique identifier of the parent category (nullable, null if root category).
	 */
	private final CategoryId parentCategoryId;

	/**
	 * Constructs a new Category object with the given parameters.
	 *
	 * @param categoryId the unique identifier of the category
	 * @param name the name of the category
	 * @param path the path of the category in the hierarchy
	 * @param description the description of the category (nullable)
	 * @param parentCategoryId the unique identifier of the parent category (nullable)
	 */
	Category(CategoryId categoryId, Name name, Path path, Description description, CategoryId parentCategoryId) {
		this.categoryId = categoryId;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentCategoryId = parentCategoryId;
	}

	/**
	 * Returns the unique identifier of the category.
	 *
	 * @return the unique identifier of the category
	 */
	public CategoryId getCategoryId() {
		return categoryId;
	}

	/**
	 * Returns the name of the category.
	 *
	 * @return the name of the category
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Returns the path of the category in the hierarchy.
	 *
	 * @return the path of the category
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Returns the description of the category.
	 *
	 * @return the description of the category (may be null)
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Returns the unique identifier of the parent category.
	 *
	 * @return the unique identifier of the parent category (null if root category)
	 */
	public CategoryId getParentCategoryId() {
		return parentCategoryId;
	}

}

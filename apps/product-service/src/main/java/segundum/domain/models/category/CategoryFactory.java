package segundum.domain.models.category;

/**
 * Factory class for creating Category objects.
 */
public class CategoryFactory {

	/**
	 * Private constructor to prevent instantiation of the CategoryFactory class.
	 */
	private CategoryFactory() {
	}

	/**
	 * Creates a new Category object.
	 *
	 * @param categoryId the unique identifier of the category
	 * @param name the name of the category
	 * @param path the path of the category in the hierarchy
	 * @param description the description of the category (nullable)
	 * @param parentCategoryId the unique identifier of the parent category (nullable)
	 * @return a new Category object
	 */
	public static Category create(CategoryId categoryId, Name name, Path path, Description description, CategoryId parentCategoryId) {
		return new Category(categoryId, name, path, description, parentCategoryId);
	}

	/**
	 * Reconstitutes a Category object from persistence.
	 *
	 * @param categoryId the unique identifier of the category
	 * @param name the name of the category
	 * @param path the path of the category in the hierarchy
	 * @param description the description of the category (nullable)
	 * @param parentCategoryId the unique identifier of the parent category (nullable)
	 * @return the reconstituted Category object
	 */
	public static Category reconstitute(CategoryId categoryId, Name name, Path path, Description description, CategoryId parentCategoryId) {
		return new Category(categoryId, name, path, description, parentCategoryId);
	}

}

package segundum.application.readmodels.category;

/**
 * Read model for category data.
 * Used by the read side to avoid leaking domain entities.
 */
public class CategoryReadModel {

	private final String categoryId;
	private final String name;
	private final String path;
	private final String description;
	private final String parentCategoryId;

	/**
	 * Constructs a new CategoryReadModel.
	 *
	 * @param categoryId       the category identifier
	 * @param name             the category name
	 * @param path             the materialized path
	 * @param description      the description (nullable)
	 * @param parentCategoryId the parent category identifier (nullable)
	 */
	public CategoryReadModel(String categoryId, String name, String path,
			String description, String parentCategoryId) {
		this.categoryId = categoryId;
		this.name = name;
		this.path = path;
		this.description = description;
		this.parentCategoryId = parentCategoryId;
	}

	public String getCategoryId() { return categoryId; }
	public String getName() { return name; }
	public String getPath() { return path; }
	public String getDescription() { return description; }
	public String getParentCategoryId() { return parentCategoryId; }

}

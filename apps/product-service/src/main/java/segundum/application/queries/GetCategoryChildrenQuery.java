package segundum.application.queries;

import segundum.domain.models.category.CategoryId;

/**
 * Represents a query to retrieve children of a category.
 */
public class GetCategoryChildrenQuery {

	private final CategoryId categoryId;

	/**
	 * Constructs a new GetCategoryChildrenQuery with the given category identifier.
	 *
	 * @param categoryId the unique identifier of the parent category
	 */
	public GetCategoryChildrenQuery(CategoryId categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Returns the unique identifier of the parent category.
	 *
	 * @return the unique identifier of the parent category
	 */
	public CategoryId getCategoryId() {
		return categoryId;
	}

}

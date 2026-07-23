package segundum.application.queries;

import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ConditionStatus;

public class SearchProductsQuery {

	private final CategoryId categoryId;
	private final String descriptionText;
	private final ConditionStatus status;
	private final Price maxPrice;
	private final int pageNumber;
	private final int pageSize;

	public SearchProductsQuery(CategoryId categoryId, String descriptionText,
			ConditionStatus status, Price maxPrice, int pageNumber, int pageSize) {
		this.categoryId = categoryId;
		this.descriptionText = descriptionText;
		this.status = status;
		this.maxPrice = maxPrice;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	/** @param categoryId identifier of the category (nullable) */
	public CategoryId getCategoryId() { return categoryId; }

	/** @param descriptionText text to search in description (nullable) */
	public String getDescriptionText() { return descriptionText; }

	/** @param status product status filter (nullable) */
	public ConditionStatus getStatus() { return status; }

	/** @param maxPrice maximum price filter (nullable) */
	public Price getMaxPrice() { return maxPrice; }

	public int getPageNumber() { return pageNumber; }

	public int getPageSize() { return pageSize; }

}

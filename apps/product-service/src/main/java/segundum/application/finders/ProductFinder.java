package segundum.application.finders;

import java.util.Optional;

import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductBasicInfo;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.readmodels.product.SellerProduct;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.seller.SellerId;

/**
 * Finder interface for querying product read models.
 */
public interface ProductFinder {

	/**
	 * Finds a product detail by its identifier.
	 *
	 * @param id the product identifier
	 * @return the product detail, if it exists
	 */
	Optional<ProductDetail> findById(ProductId id);

	/**
	 * Finds the basic information of a product by its identifier.
	 *
	 * @param id the product identifier
	 * @return the product basic information, if it exists
	 */
	Optional<ProductBasicInfo> findBasicInfoById(ProductId id);

	/**
	 * Finds the products published in a given month and year.
	 *
	 * @param month the month
	 * @param year the year
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return a page of product summaries
	 */
	Page<ProductSummary> findByMonthAndYear(int month, int year, int pageNumber, int pageSize);

	/**
	 * Searches products matching the given filters.
	 *
	 * @param categoryId the category identifier
	 * @param descriptionText the description text
	 * @param status the condition status
	 * @param maxPrice the maximum price
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return a page of product search results
	 */
	Page<ProductSearchResult> search(CategoryId categoryId, String descriptionText,
			ConditionStatus status, Price maxPrice, int pageNumber, int pageSize);

	/**
	 * Finds the draft products of a seller.
	 *
	 * @param sellerId the seller identifier
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return a page of seller products
	 */
	Page<SellerProduct> findDraftsBySeller(SellerId sellerId, int pageNumber, int pageSize);

	/**
	 * Finds the products on sale of a seller.
	 *
	 * @param sellerId the seller identifier
	 * @param pageNumber the page number
	 * @param pageSize the page size
	 * @return a page of seller products
	 */
	Page<SellerProduct> findForSaleBySeller(SellerId sellerId, int pageNumber, int pageSize);

}

package segundum.application.usecases;

import segundum.application.queries.GetSellerForSaleProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;

/**
 * Represents the use case to obtain the products on sale of a seller.
 */
public interface GetSellerForSaleProductsUseCase {

	/**
	 * Executes the query to obtain the products on sale of a seller.
	 *
	 * @param query the seller for sale products query
	 * @return a page of seller products
	 */
	Page<SellerProduct> execute(GetSellerForSaleProductsQuery query);

}

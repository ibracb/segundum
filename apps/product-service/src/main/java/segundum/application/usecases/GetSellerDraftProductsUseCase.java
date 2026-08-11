package segundum.application.usecases;

import segundum.application.queries.GetSellerDraftProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;

/**
 * Represents the use case to obtain the draft products of a seller.
 */
public interface GetSellerDraftProductsUseCase {

	/**
	 * Executes the query to obtain the draft products of a seller.
	 *
	 * @param query the seller draft products query
	 * @return a page of seller products
	 */
	Page<SellerProduct> execute(GetSellerDraftProductsQuery query);

}

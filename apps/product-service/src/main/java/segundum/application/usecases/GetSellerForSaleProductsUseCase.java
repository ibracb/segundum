package segundum.application.usecases;

import segundum.application.queries.GetSellerForSaleProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;

public interface GetSellerForSaleProductsUseCase {

	Page<SellerProduct> execute(GetSellerForSaleProductsQuery query);

}

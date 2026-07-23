package segundum.application.usecases;

import segundum.application.queries.GetSellerDraftProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;

public interface GetSellerDraftProductsUseCase {

	Page<SellerProduct> execute(GetSellerDraftProductsQuery query);

}

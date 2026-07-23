package segundum.application.usecases.interactors;

import segundum.application.queries.GetSellerForSaleProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetSellerForSaleProductsUseCase;

public class GetSellerForSaleProductsInteractor implements GetSellerForSaleProductsUseCase {

	private final ProductReadRepository productReadRepository;

	public GetSellerForSaleProductsInteractor(ProductReadRepository productReadRepository) {
		this.productReadRepository = productReadRepository;
	}

	@Override
	public Page<SellerProduct> execute(GetSellerForSaleProductsQuery query) {
		return productReadRepository.findForSaleBySeller(
				query.getSellerId(),
				query.getPageNumber(),
				query.getPageSize());
	}

}

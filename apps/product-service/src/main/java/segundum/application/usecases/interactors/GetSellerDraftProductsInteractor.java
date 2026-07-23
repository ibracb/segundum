package segundum.application.usecases.interactors;

import segundum.application.queries.GetSellerDraftProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetSellerDraftProductsUseCase;

public class GetSellerDraftProductsInteractor implements GetSellerDraftProductsUseCase {

	private final ProductReadRepository productReadRepository;

	public GetSellerDraftProductsInteractor(ProductReadRepository productReadRepository) {
		this.productReadRepository = productReadRepository;
	}

	@Override
	public Page<SellerProduct> execute(GetSellerDraftProductsQuery query) {
		return productReadRepository.findDraftsBySeller(
				query.getSellerId(),
				query.getPageNumber(),
				query.getPageSize());
	}

}

package segundum.application.usecases.interactors;

import segundum.application.queries.GetSellerForSaleProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetSellerForSaleProductsUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;
import segundum.domain.models.seller.Seller;
import segundum.domain.repositories.SellerRepository;

public class GetSellerForSaleProductsInteractor implements GetSellerForSaleProductsUseCase {

	private final ProductReadRepository productReadRepository;
	private final SellerRepository sellerRepository;

	public GetSellerForSaleProductsInteractor(ProductReadRepository productReadRepository,
			SellerRepository sellerRepository) {
		this.productReadRepository = productReadRepository;
		this.sellerRepository = sellerRepository;
	}

	@Override
	public Page<SellerProduct> execute(GetSellerForSaleProductsQuery query) {
		Seller seller = sellerRepository.findById(query.getSellerId())
				.orElseThrow(() -> new EntityNotFoundException("Seller", query.getSellerId().getValue().toString()));
		if (!seller.isActive()) {
			throw new SellerNotActiveException(query.getSellerId());
		}
		return productReadRepository.findForSaleBySeller(
				query.getSellerId(),
				query.getPageNumber(),
				query.getPageSize());
	}

}

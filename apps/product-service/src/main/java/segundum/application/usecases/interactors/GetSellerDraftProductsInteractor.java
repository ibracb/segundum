package segundum.application.usecases.interactors;

import segundum.application.queries.GetSellerDraftProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetSellerDraftProductsUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;
import segundum.domain.models.seller.Seller;
import segundum.domain.repositories.SellerRepository;

/**
 * Represents the interactor for obtaining the draft products of a seller.
 */
public class GetSellerDraftProductsInteractor implements GetSellerDraftProductsUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductReadRepository productReadRepository;
	/**
	 * The repository used to read sellers.
	 */
	private final SellerRepository sellerRepository;

	/**
	 * Constructs a new GetSellerDraftProductsInteractor with the given repositories.
	 *
	 * @param productReadRepository the product read repository
	 * @param sellerRepository the seller repository
	 */
	public GetSellerDraftProductsInteractor(ProductReadRepository productReadRepository,
			SellerRepository sellerRepository) {
		this.productReadRepository = productReadRepository;
		this.sellerRepository = sellerRepository;
	}

	@Override
	public Page<SellerProduct> execute(GetSellerDraftProductsQuery query) {
		Seller seller = sellerRepository.findById(query.getSellerId())
				.orElseThrow(() -> new EntityNotFoundException("Seller", query.getSellerId().getValue().toString()));
		if (!seller.isActive()) {
			throw new SellerNotActiveException(query.getSellerId());
		}
		return productReadRepository.findDraftsBySeller(
				query.getSellerId(),
				query.getPageNumber(),
				query.getPageSize());
	}

}

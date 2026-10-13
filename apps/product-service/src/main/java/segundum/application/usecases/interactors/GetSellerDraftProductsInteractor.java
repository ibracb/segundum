package segundum.application.usecases.interactors;

import segundum.application.queries.GetSellerDraftProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.finders.ProductFinder;
import segundum.application.finders.SellerFinder;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.application.usecases.GetSellerDraftProductsUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;

/**
 * Represents the interactor for obtaining the draft products of a seller.
 */
public class GetSellerDraftProductsInteractor implements GetSellerDraftProductsUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductFinder productFinder;
	
	/**
	 * The finder used to read seller data.
	 */
	private final SellerFinder sellerFinder;

	/**
	 * Constructs a new GetSellerDraftProductsInteractor with the given finders.
	 *
	 * @param productFinder the product read repository
	 * @param sellerFinder the seller read repository
	 */
	public GetSellerDraftProductsInteractor(ProductFinder productFinder,
			SellerFinder sellerFinder) {
		this.productFinder = productFinder;
		this.sellerFinder = sellerFinder;
	}

	@Override
	public Page<SellerProduct> execute(GetSellerDraftProductsQuery query) {
		SellerReadModel seller = sellerFinder.findById(query.getSellerId())
				.orElseThrow(() -> new EntityNotFoundException("Seller", "ID", query.getSellerId().getValue().toString()));
		if (!seller.isActive()) {
			throw new SellerNotActiveException(query.getSellerId());
		}
		return productFinder.findDraftsBySeller(
				query.getSellerId(),
				query.getPageNumber(),
				query.getPageSize());
	}

}

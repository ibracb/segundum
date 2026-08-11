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

/**
 * Represents the interactor for obtaining the products on sale of a seller.
 */
public class GetSellerForSaleProductsInteractor implements GetSellerForSaleProductsUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductReadRepository productReadRepository;
	/**
	 * The repository used to read sellers.
	 */
	private final SellerRepository sellerRepository;

	/**
	 * Constructs a new GetSellerForSaleProductsInteractor with the given repositories.
	 *
	 * @param productReadRepository the product read repository
	 * @param sellerRepository the seller repository
	 */
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

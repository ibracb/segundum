package segundum.application.usecases.interactors;

import segundum.application.queries.GetMonthlyHistoryQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.finders.ProductFinder;
import segundum.application.usecases.GetMonthlyHistoryUseCase;

/**
 * Represents the interactor for obtaining the monthly history of products.
 */
public class GetMonthlyHistoryInteractor implements GetMonthlyHistoryUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductFinder productFinder;

	/**
	 * Constructs a new GetMonthlyHistoryInteractor with the given repository.
	 *
	 * @param productFinder the product read repository
	 */
	public GetMonthlyHistoryInteractor(ProductFinder productFinder) {
		this.productFinder = productFinder;
	}

	@Override
	public Page<ProductSummary> execute(GetMonthlyHistoryQuery query) {
		return productFinder.findByMonthAndYear(
				query.getMonth(),
				query.getYear(),
				query.getPageNumber(),
				query.getPageSize());
	}

}

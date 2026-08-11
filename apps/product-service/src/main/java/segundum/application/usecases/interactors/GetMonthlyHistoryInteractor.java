package segundum.application.usecases.interactors;

import segundum.application.queries.GetMonthlyHistoryQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetMonthlyHistoryUseCase;

/**
 * Represents the interactor for obtaining the monthly history of products.
 */
public class GetMonthlyHistoryInteractor implements GetMonthlyHistoryUseCase {

	/**
	 * The repository used to read products.
	 */
	private final ProductReadRepository productReadRepository;

	/**
	 * Constructs a new GetMonthlyHistoryInteractor with the given repository.
	 *
	 * @param productReadRepository the product read repository
	 */
	public GetMonthlyHistoryInteractor(ProductReadRepository productReadRepository) {
		this.productReadRepository = productReadRepository;
	}

	@Override
	public Page<ProductSummary> execute(GetMonthlyHistoryQuery query) {
		return productReadRepository.findByMonthAndYear(
				query.getMonth(),
				query.getYear(),
				query.getPageNumber(),
				query.getPageSize());
	}

}

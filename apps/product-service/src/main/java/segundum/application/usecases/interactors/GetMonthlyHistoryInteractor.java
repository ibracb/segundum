package segundum.application.usecases.interactors;

import segundum.application.queries.GetMonthlyHistoryQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetMonthlyHistoryUseCase;

public class GetMonthlyHistoryInteractor implements GetMonthlyHistoryUseCase {

	private final ProductReadRepository productReadRepository;

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

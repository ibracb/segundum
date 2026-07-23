package segundum.application.usecases.interactors;

import java.util.Optional;

import segundum.application.queries.GetProductDetailQuery;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.GetProductDetailUseCase;

public class GetProductDetailInteractor implements GetProductDetailUseCase {

	private final ProductReadRepository productReadRepository;

	public GetProductDetailInteractor(ProductReadRepository productReadRepository) {
		this.productReadRepository = productReadRepository;
	}

	@Override
	public Optional<ProductDetail> execute(GetProductDetailQuery query) {
		return productReadRepository.findById(query.getProductId());
	}

}

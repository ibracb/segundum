package segundum.application.usecases;

import java.util.Optional;

import segundum.application.queries.GetProductDetailQuery;
import segundum.application.readmodels.product.ProductDetail;

public interface GetProductDetailUseCase {

	Optional<ProductDetail> execute(GetProductDetailQuery query);

}

package segundum.application.usecases;

import java.util.Optional;

import segundum.application.queries.GetProductDetailQuery;
import segundum.application.readmodels.product.ProductDetail;

/**
 * Represents the use case to obtain the detail of a product.
 */
public interface GetProductDetailUseCase {

	/**
	 * Executes the query to obtain the detail of a product.
	 *
	 * @param query the product detail query
	 * @return the detail of the product, if it exists
	 */
	Optional<ProductDetail> execute(GetProductDetailQuery query);

}

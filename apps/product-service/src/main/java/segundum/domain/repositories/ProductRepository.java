package segundum.domain.repositories;

import java.util.Optional;

import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;

/**
 * Repository interface for write operations on Product entities.
 */
public interface ProductRepository {

	/**
	 * Creates a new product in the repository.
	 *
	 * @param product the product to create
	 */
	void create(Product product);

	/**
	 * Updates an existing product in the repository.
	 *
	 * @param product the product to update
	 */
	void update(Product product);
	
	/**
	 * Finds a product by their unique identifier.
	 *
	 * @param id the unique identifier of the product
	 * @return an Optional containing the found product, or empty if not found
	 */
	Optional<Product> findById(ProductId id);

}

package segundum.infrastructure.persistence.fakes.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;
import segundum.domain.repositories.ProductRepository;

public class FakeProductRepository implements ProductRepository {

	private final Map<ProductId, Product> products = new HashMap<>();

	@Override
	public void create(Product product) {
		products.put(product.getProductId(), product);
	}

	@Override
	public void update(Product product) {
		products.put(product.getProductId(), product);
	}
	
	@Override
	public Optional<Product> findById(ProductId id) {
		return Optional.ofNullable(products.get(id));
	}
	
	public Map<ProductId, Product> getAll() {
		return products;
	}
}

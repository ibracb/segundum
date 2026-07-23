package segundum.infrastructure.persistence.fakes.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import segundum.domain.models.product.Product;
import segundum.domain.models.product.ProductId;
import segundum.domain.repositories.ProductWriteRepository;

public class FakeProductWriteRepository implements ProductWriteRepository {

	private final Map<ProductId, Product> products = new HashMap<>();

	@Override
	public Optional<Product> findById(ProductId id) {
		return Optional.ofNullable(products.get(id));
	}

	@Override
	public void create(Product product) {
		products.put(product.getProductId(), product);
	}

	@Override
	public void update(Product product) {
		products.put(product.getProductId(), product);
	}

	@Override
	public void delete(ProductId id) {
		products.remove(id);
	}

	public Map<ProductId, Product> getAll() {
		return products;
	}
}

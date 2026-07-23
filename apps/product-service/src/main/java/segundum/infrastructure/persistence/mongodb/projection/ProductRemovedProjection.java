package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.ProductRemoved;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

@Component
public class ProductRemovedProjection {

	private final ProductReadMongoRepository repository;

	public ProductRemovedProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@EventListener
	@Async("projectionTaskExecutor")
	public void on(ProductRemoved event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			doc.setSaleStatus("DELETED");
			repository.save(doc);
		}
	}

}

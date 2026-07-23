package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.ProductTakenDown;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

@Component
public class ProductTakenDownProjection {

	private final ProductReadMongoRepository repository;

	public ProductTakenDownProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@EventListener
	@Async("projectionTaskExecutor")
	public void on(ProductTakenDown event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			doc.setSaleStatus("DRAFT");
			repository.save(doc);
		}
	}

}

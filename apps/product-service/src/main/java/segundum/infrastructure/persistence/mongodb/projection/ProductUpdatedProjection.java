package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.ProductUpdated;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

@Component
public class ProductUpdatedProjection {

	private final ProductReadMongoRepository repository;

	public ProductUpdatedProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@EventListener
	@Async("projectionTaskExecutor")
	public void on(ProductUpdated event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			if (event.getPrice() != null) {
				doc.setPrice(event.getPrice().getValue());
			}
			if (event.getDescription() != null) {
				doc.setDescription(event.getDescription().getValue());
			}
			if (event.getConditionStatus() != null) {
				doc.setConditionStatus(event.getConditionStatus().name());
			}
			repository.save(doc);
		}
	}

}

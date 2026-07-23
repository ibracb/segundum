package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.PickupLocationAssigned;
import segundum.infrastructure.persistence.mongodb.product.PickupLocationDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

@Component
public class PickupLocationAssignedProjection {

	private final ProductReadMongoRepository repository;

	public PickupLocationAssignedProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@EventListener
	@Async("projectionTaskExecutor")
	public void on(PickupLocationAssigned event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			PickupLocationDocument pickupDoc = new PickupLocationDocument(
					event.getPickupLocation().getDescription(),
					event.getPickupLocation().getLatitude(),
					event.getPickupLocation().getLongitude());
			doc.setPickupLocation(pickupDoc);
			repository.save(doc);
		}
	}

}

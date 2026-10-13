package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.SellerUpdated;
import segundum.infrastructure.persistence.mongodb.seller.SellerReadDocument;
import segundum.infrastructure.persistence.mongodb.seller.SellerReadMongoRepository;

/**
 * Represents the projection that updates the read side when a seller is updated.
 */
@Component
public class SellerUpdatedProjection {

	/**
	 * The repository used to persist seller read documents.
	 */
	private final SellerReadMongoRepository repository;

	/**
	 * Constructs a new SellerUpdatedProjection with the given repository.
	 *
	 * @param repository the seller read Mongo repository
	 */
	public SellerUpdatedProjection(SellerReadMongoRepository repository) {
		this.repository = repository;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(SellerUpdated event) {
		String sellerId = event.getSellerId().getValue().toString();
		Optional<SellerReadDocument> optional = repository.findById(sellerId);
		if (optional.isPresent()) {
			SellerReadDocument doc = optional.get();
			doc.setName(event.getName().getValue());
			doc.setSurname(event.getSurname().getValue());
			repository.save(doc);
		}
	}

}

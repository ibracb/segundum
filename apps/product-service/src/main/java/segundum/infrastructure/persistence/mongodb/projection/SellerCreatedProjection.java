package segundum.infrastructure.persistence.mongodb.projection;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.SellerCreated;
import segundum.infrastructure.persistence.mongodb.seller.SellerReadDocument;
import segundum.infrastructure.persistence.mongodb.seller.SellerReadMongoRepository;

/**
 * Represents the projection that creates the read side document when a seller is created.
 */
@Component
public class SellerCreatedProjection {

	/**
	 * The repository used to persist seller read documents.
	 */
	private final SellerReadMongoRepository repository;

	/**
	 * Constructs a new SellerCreatedProjection with the given repository.
	 *
	 * @param repository the seller read Mongo repository
	 */
	public SellerCreatedProjection(SellerReadMongoRepository repository) {
		this.repository = repository;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(SellerCreated event) {
		SellerReadDocument doc = new SellerReadDocument(
				event.getSellerId().getValue().toString(),
				event.getName().getValue(),
				event.getSurname().getValue(),
				event.getEmail().getValue(),
				true);
		repository.save(doc);
	}

}

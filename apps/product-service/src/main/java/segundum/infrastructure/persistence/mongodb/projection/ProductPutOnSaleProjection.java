package segundum.infrastructure.persistence.mongodb.projection;

import java.util.Optional;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import segundum.domain.events.ProductPutOnSale;
import segundum.infrastructure.persistence.mongodb.product.ProductReadDocument;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

/**
 * Represents the projection that updates the read side when a product is put on sale.
 */
@Component
public class ProductPutOnSaleProjection {

	/**
	 * The repository used to persist product read documents.
	 */
	private final ProductReadMongoRepository repository;

	/**
	 * Constructs a new ProductPutOnSaleProjection with the given repository.
	 *
	 * @param repository the product read Mongo repository
	 */
	public ProductPutOnSaleProjection(ProductReadMongoRepository repository) {
		this.repository = repository;
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Async("projectionTaskExecutor")
	public void on(ProductPutOnSale event) {
		String productId = event.getProductId().getValue().toString();
		Optional<ProductReadDocument> optional = repository.findById(productId);
		if (optional.isPresent()) {
			ProductReadDocument doc = optional.get();
			doc.setSaleStatus("FOR_SALE");
			repository.save(doc);
		}
	}

}

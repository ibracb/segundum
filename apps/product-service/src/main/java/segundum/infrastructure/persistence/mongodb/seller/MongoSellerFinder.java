package segundum.infrastructure.persistence.mongodb.seller;

import java.util.Optional;

import segundum.application.finders.SellerFinder;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.domain.models.seller.SellerId;

/**
 * Represents the MongoDB implementation of the seller read repository.
 */
public class MongoSellerFinder implements SellerFinder {

	/**
	 * The Spring Data repository for seller read documents.
	 */
	private final SellerReadMongoRepository mongoRepository;

	/**
	 * Constructs a new MongoSellerFinder with the given repository.
	 *
	 * @param mongoRepository the seller read Mongo repository
	 */
	public MongoSellerFinder(SellerReadMongoRepository mongoRepository) {
		this.mongoRepository = mongoRepository;
	}

	@Override
	public Optional<SellerReadModel> findById(SellerId id) {
		return mongoRepository.findById(id.getValue().toString())
				.map(doc -> new SellerReadModel(
						doc.getSellerId(),
						doc.getName(),
						doc.getSurname(),
						doc.getEmail(),
						doc.isActive()));
	}

}

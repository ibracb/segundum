package segundum.infrastructure.persistence.mongodb.seller;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for SellerReadDocument.
 */
public interface SellerReadMongoRepository extends MongoRepository<SellerReadDocument, String> {

}

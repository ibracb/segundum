package segundum.infrastructure.persistence.mongodb.sale;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for SaleReadDocument.
 */
public interface SaleReadMongoRepository extends MongoRepository<SaleReadDocument, String> {

}

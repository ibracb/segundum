package segundum.infrastructure.persistence.mongodb.product;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for ProductReadDocument.
 */
public interface ProductReadMongoRepository extends MongoRepository<ProductReadDocument, String> {

}

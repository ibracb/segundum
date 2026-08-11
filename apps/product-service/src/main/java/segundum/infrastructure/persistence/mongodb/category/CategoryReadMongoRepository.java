package segundum.infrastructure.persistence.mongodb.category;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Represents the Spring Data repository for category read documents.
 */
public interface CategoryReadMongoRepository extends MongoRepository<CategoryReadDocument, String> {

}

package segundum.infrastructure.persistence.mongodb.category;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryReadMongoRepository extends MongoRepository<CategoryReadDocument, String> {

}

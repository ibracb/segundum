package segundum.infrastructure.persistence.jpa.event;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Represents the JPA repository for stored events.
 */
public interface StoredEventJpaRepository extends CrudRepository<StoredEventJpaEntity, String> {

    /**
     * Finds the stored events of an aggregate ordered by timestamp.
     *
     * @param aggregateId the identifier of the aggregate
     * @return the list of stored events
     */
    List<StoredEventJpaEntity> findByAggregateIdOrderByTimestamp(String aggregateId);

    /**
     * Finds the proposed sale events for the given product and purchaser.
     *
     * @param productId   the identifier of the product
     * @param purchaserId the identifier of the purchaser
     * @return the list of proposed sale events
     */
    @Query(value = "SELECT * FROM event_store WHERE type = 'SaleProposed' "
            + "AND JSON_UNQUOTE(JSON_EXTRACT(payload, '$.productId')) = :productId "
            + "AND JSON_UNQUOTE(JSON_EXTRACT(payload, '$.purchaserId')) = :purchaserId",
            nativeQuery = true)
    List<StoredEventJpaEntity> findSaleProposedByProductAndPurchaser(
            @Param("productId") String productId, @Param("purchaserId") String purchaserId);

}

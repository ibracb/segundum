package segundum.infrastructure.persistence.mongodb.sale;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.readmodels.sale.SaleDetailReadModel;
import segundum.application.finders.SaleFinder;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SellerId;

/**
 * MongoDB implementation of the SaleFinder.
 */
public class MongoSaleFinder implements SaleFinder {

	private final MongoTemplate mongoTemplate;

	public MongoSaleFinder(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Page<SaleAsPurchaserReadModel> findByPurchaserId(PurchaserId purchaserId, OrderStatus status,
			int pageNumber, int pageSize) {
		Query query = new Query(Criteria.where("purchaser_id").is(purchaserId.getValue().toString()));
		if (status != null) {
			query.addCriteria(Criteria.where("status").is(status.name()));
		}
		return executePaginated(query, pageNumber, pageSize, SaleReadMapper::toSaleAsPurchaser);
	}

	@Override
	public Page<SaleAsSellerReadModel> findBySellerId(SellerId sellerId, OrderStatus status,
			int pageNumber, int pageSize) {
		Query query = new Query(Criteria.where("seller_id").is(sellerId.getValue().toString()));
		if (status != null) {
			query.addCriteria(Criteria.where("status").is(status.name()));
		}
		return executePaginated(query, pageNumber, pageSize, SaleReadMapper::toSaleAsSeller);
	}

	@Override
	public Page<SaleDetailReadModel> searchSales(PurchaserId purchaserId, SellerId sellerId,
			OrderStatus status, int pageNumber, int pageSize) {
		Query query = new Query();
		if (purchaserId != null) {
			query.addCriteria(Criteria.where("purchaser_id").is(purchaserId.getValue().toString()));
		}
		if (sellerId != null) {
			query.addCriteria(Criteria.where("seller_id").is(sellerId.getValue().toString()));
		}
		if (status != null) {
			query.addCriteria(Criteria.where("status").is(status.name()));
		}
		return executePaginated(query, pageNumber, pageSize, SaleReadMapper::toSaleDetail);
	}

	private <T> Page<T> executePaginated(Query query, int pageNumber, int pageSize,
			Function<SaleReadDocument, T> mapper) {
		query.with(Sort.by(Sort.Direction.DESC, "datetime"));
		query.with(PageRequest.of(pageNumber, pageSize));

		long total = mongoTemplate.count(query, SaleReadDocument.class);
		List<T> content = mongoTemplate.find(query, SaleReadDocument.class).stream()
				.map(mapper)
				.collect(Collectors.toList());
		return new Page<>(content, total, pageNumber, pageSize);
	}

}

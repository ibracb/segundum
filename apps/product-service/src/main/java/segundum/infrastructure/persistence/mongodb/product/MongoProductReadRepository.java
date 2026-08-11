package segundum.infrastructure.persistence.mongodb.product;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductBasicInfo;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.repositories.ProductReadRepository;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Price;
import segundum.domain.models.product.ProductId;
import segundum.domain.models.seller.SellerId;

/**
 * Represents the MongoDB implementation of the product read repository.
 */
public class MongoProductReadRepository implements ProductReadRepository {

	/**
	 * The Spring Data repository for product read documents.
	 */
	private final ProductReadMongoRepository mongoRepository;
	/**
	 * The MongoDB template.
	 */
	private final MongoTemplate mongoTemplate;

	/**
	 * Constructs a new MongoProductReadRepository with the given dependencies.
	 *
	 * @param mongoRepository the product read Mongo repository
	 * @param mongoTemplate the MongoDB template
	 */
	public MongoProductReadRepository(ProductReadMongoRepository mongoRepository,
			MongoTemplate mongoTemplate) {
		this.mongoRepository = mongoRepository;
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Optional<ProductBasicInfo> findBasicInfoById(ProductId id) {
		return mongoRepository.findById(id.getValue().toString())
				.map(ProductReadMapper::toBasicInfo);
	}

	@Override
	public Optional<ProductDetail> findById(ProductId id) {
		return mongoRepository.findById(id.getValue().toString())
				.map(ProductReadMapper::toDetail);
	}

	@Override
	public Page<ProductSummary> findByMonthAndYear(int month, int year, int pageNumber, int pageSize) {
		LocalDate start = LocalDate.of(year, month, 1);
		LocalDate end = start.plusMonths(1);
		Instant startInstant = start.atStartOfDay().toInstant(ZoneOffset.UTC);
		Instant endInstant = end.atStartOfDay().toInstant(ZoneOffset.UTC);

		Query query = new Query();
		query.addCriteria(Criteria.where("sale_status").is("FOR_SALE"));
		query.addCriteria(Criteria.where("publication_date").gte(startInstant).lt(endInstant));
		query.with(Sort.by(Sort.Direction.DESC, "views"));
		query.with(PageRequest.of(pageNumber, pageSize));

		long total = mongoTemplate.count(query, ProductReadDocument.class);
		List<ProductReadDocument> docs = mongoTemplate.find(query, ProductReadDocument.class);

		List<ProductSummary> content = docs.stream()
				.map(ProductReadMapper::toSummary)
				.collect(Collectors.toList());

		return new Page<>(content, total, pageNumber, pageSize);
	}

	@Override
	public Page<ProductSearchResult> search(CategoryId categoryId, String descriptionText,
			ConditionStatus status, Price maxPrice, int pageNumber, int pageSize) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sale_status").is("FOR_SALE"));

		if (categoryId != null) {
			query.addCriteria(Criteria.where("category_id").is(categoryId.getValue()));
		}
		if (descriptionText != null && !descriptionText.isBlank()) {
			query.addCriteria(Criteria.where("description").regex(descriptionText, "i"));
		}
		if (status != null) {
			List<String> validStatuses = status.equalOrBetter().stream()
					.map(Enum::name)
					.collect(Collectors.toList());
			query.addCriteria(Criteria.where("condition_status").in(validStatuses));
		}
		if (maxPrice != null) {
			query.addCriteria(Criteria.where("price").lte(maxPrice.getValue()));
		}

		query.with(Sort.by(Sort.Direction.DESC, "publication_date"));
		query.with(PageRequest.of(pageNumber, pageSize));

		long total = mongoTemplate.count(query, ProductReadDocument.class);
		List<ProductReadDocument> docs = mongoTemplate.find(query, ProductReadDocument.class);

		List<ProductSearchResult> content = docs.stream()
				.map(ProductReadMapper::toSearchResult)
				.collect(Collectors.toList());

		return new Page<>(content, total, pageNumber, pageSize);
	}

	@Override
	public Page<SellerProduct> findDraftsBySeller(SellerId sellerId, int pageNumber, int pageSize) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sale_status").is("DRAFT"));
		query.addCriteria(Criteria.where("seller_id").is(sellerId.getValue().toString()));
		query.with(Sort.by(Sort.Direction.DESC, "publication_date"));
		query.with(PageRequest.of(pageNumber, pageSize));

		long total = mongoTemplate.count(query, ProductReadDocument.class);
		List<ProductReadDocument> docs = mongoTemplate.find(query, ProductReadDocument.class);

		List<SellerProduct> content = docs.stream()
				.map(ProductReadMapper::toSellerProduct)
				.collect(Collectors.toList());

		return new Page<>(content, total, pageNumber, pageSize);
	}

	@Override
	public Page<SellerProduct> findForSaleBySeller(SellerId sellerId, int pageNumber, int pageSize) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sale_status").is("FOR_SALE"));
		query.addCriteria(Criteria.where("seller_id").is(sellerId.getValue().toString()));
		query.with(Sort.by(Sort.Direction.DESC, "publication_date"));
		query.with(PageRequest.of(pageNumber, pageSize));

		long total = mongoTemplate.count(query, ProductReadDocument.class);
		List<ProductReadDocument> docs = mongoTemplate.find(query, ProductReadDocument.class);

		List<SellerProduct> content = docs.stream()
				.map(ProductReadMapper::toSellerProduct)
				.collect(Collectors.toList());

		return new Page<>(content, total, pageNumber, pageSize);
	}

}

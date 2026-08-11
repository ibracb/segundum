package segundum.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import segundum.application.eventhandlers.SaleEventHandler;
import segundum.application.eventhandlers.UserEventHandler;
import segundum.application.eventhandlers.interactors.SaleEventHandlerInteractor;
import segundum.application.eventhandlers.interactors.UserEventHandlerInteractor;
import segundum.application.repositories.CategoryReadRepository;
import segundum.application.repositories.ProductReadRepository;
import segundum.application.usecases.AssignProductPickupLocationUseCase;
import segundum.application.usecases.CreateProductUseCase;
import segundum.application.usecases.DiscardProductUseCase;
import segundum.application.usecases.GetCategoryChildrenUseCase;
import segundum.application.usecases.GetMonthlyHistoryUseCase;
import segundum.application.usecases.GetProductBasicInfoUseCase;
import segundum.application.usecases.GetProductDetailUseCase;
import segundum.application.usecases.GetRootCategoriesUseCase;
import segundum.application.usecases.IncrementProductViewsUseCase;
import segundum.application.usecases.LoadCategoryHierarchyUseCase;
import segundum.application.usecases.PutProductForSaleUseCase;
import segundum.application.usecases.RemoveProductUseCase;
import segundum.application.usecases.SearchProductsUseCase;
import segundum.application.usecases.GetSellerDraftProductsUseCase;
import segundum.application.usecases.GetSellerForSaleProductsUseCase;
import segundum.application.usecases.TakeProductDownUseCase;
import segundum.application.usecases.UpdateProductUseCase;
import segundum.application.usecases.interactors.AssignProductPickupLocationInteractor;
import segundum.application.usecases.interactors.CreateProductInteractor;
import segundum.application.usecases.interactors.DiscardProductInteractor;
import segundum.application.usecases.interactors.GetCategoryChildrenInteractor;
import segundum.application.usecases.interactors.GetMonthlyHistoryInteractor;
import segundum.application.usecases.interactors.GetProductBasicInfoInteractor;
import segundum.application.usecases.interactors.GetProductDetailInteractor;
import segundum.application.usecases.interactors.GetRootCategoriesInteractor;
import segundum.application.usecases.interactors.IncrementProductViewsInteractor;
import segundum.application.usecases.interactors.LoadCategoryHierarchyInteractor;
import segundum.application.usecases.interactors.PutProductForSaleInteractor;
import segundum.application.usecases.interactors.RemoveProductInteractor;
import segundum.application.usecases.interactors.SearchProductsInteractor;
import segundum.application.usecases.interactors.GetSellerDraftProductsInteractor;
import segundum.application.usecases.interactors.GetSellerForSaleProductsInteractor;
import segundum.application.usecases.interactors.TakeProductDownInteractor;
import segundum.application.usecases.interactors.UpdateProductInteractor;
import segundum.domain.outbound.CategoryHierarchyLoader;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.CategoryWriteRepository;
import segundum.domain.repositories.ProductWriteRepository;
import segundum.domain.repositories.SellerRepository;
import segundum.infrastructure.logging.Slf4jLogEmitter;
import segundum.infrastructure.persistence.mongodb.category.CategoryReadMongoRepository;
import segundum.infrastructure.persistence.mongodb.category.MongoCategoryReadRepository;
import segundum.infrastructure.persistence.mongodb.product.MongoProductReadRepository;
import segundum.infrastructure.persistence.mongodb.product.ProductReadMongoRepository;

/**
 * Spring configuration for application handler beans.
 */
@Configuration
public class ApplicationConfig {

	@Bean
	public LogEmitter logEmitter() {
		return new Slf4jLogEmitter(ApplicationConfig.class);
	}

	@Bean
	public ProductReadRepository productReadRepository(
			ProductReadMongoRepository mongoRepository,
			MongoTemplate mongoTemplate) {
		return new MongoProductReadRepository(mongoRepository, mongoTemplate);
	}

	@Bean
	public CategoryReadRepository categoryReadRepository(
			CategoryReadMongoRepository mongoRepository,
			MongoTemplate mongoTemplate) {
		return new MongoCategoryReadRepository(mongoTemplate);
	}

	@Bean
	public CreateProductUseCase createProductUseCase(CategoryWriteRepository categoryWriteRepository,
			SellerRepository sellerRepository, ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		return new CreateProductInteractor(categoryWriteRepository, sellerRepository,
				productWriteRepository, domainEventPublisher);
	}

	@Bean
	public UpdateProductUseCase updateProductUseCase(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		return new UpdateProductInteractor(productWriteRepository, domainEventPublisher);
	}

	@Bean
	public DiscardProductUseCase discardProductUseCase(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		return new DiscardProductInteractor(productWriteRepository, domainEventPublisher);
	}

	@Bean
	public PutProductForSaleUseCase putProductOnSaleUseCase(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		return new PutProductForSaleInteractor(productWriteRepository, domainEventPublisher);
	}

	@Bean
	public TakeProductDownUseCase takeProductDownUseCase(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		return new TakeProductDownInteractor(productWriteRepository, domainEventPublisher);
	}

	@Bean
	public RemoveProductUseCase removeProductUseCase(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		return new RemoveProductInteractor(productWriteRepository, domainEventPublisher);
	}

	@Bean
	public AssignProductPickupLocationUseCase assignProductPickupLocationUseCase(
			ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		return new AssignProductPickupLocationInteractor(productWriteRepository, domainEventPublisher);
	}

	@Bean
	public IncrementProductViewsUseCase incrementProductViewsUseCase(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher) {
		return new IncrementProductViewsInteractor(productWriteRepository, domainEventPublisher);
	}

	/**
	 * Instantiates the sales bounded context handler.
	 *
	 * @param productWriteRepository the repository for writing product data
	 * @param domainEventPublisher the publisher for domain events
	 * @param logEmitter the logger
	 * @return the sales handler
	 */
	@Bean
	public SaleEventHandler salesHandler(ProductWriteRepository productWriteRepository,
			DomainEventPublisher domainEventPublisher, LogEmitter logEmitter) {
		return new SaleEventHandlerInteractor(productWriteRepository, domainEventPublisher, logEmitter);
	}

	/**
	 * Instantiates the users bounded context handler.
	 *
	 * @param sellerRepository the repository for managing seller data
	 * @param logEmitter the logger
	 * @return the users handler
	 */
	@Bean
	public UserEventHandler usersHandler(SellerRepository sellerRepository, LogEmitter logEmitter) {
		return new UserEventHandlerInteractor(sellerRepository, logEmitter);
	}

	@Bean
	public LoadCategoryHierarchyUseCase loadCategoryHierarchyUseCase(CategoryHierarchyLoader categoryHierarchyLoader) {
		return new LoadCategoryHierarchyInteractor(categoryHierarchyLoader);
	}

	/**
	 * Instantiates the get root categories use case.
	 *
	 * @param categoryReadRepository the repository for reading category data
	 * @return the get root categories use case
	 */
	@Bean
	public GetRootCategoriesUseCase getRootCategoriesUseCase(CategoryReadRepository categoryReadRepository) {
		return new GetRootCategoriesInteractor(categoryReadRepository);
	}

	/**
	 * Instantiates the get category children use case.
	 *
	 * @param categoryReadRepository the repository for reading category data
	 * @return the get category children use case
	 */
	@Bean
	public GetCategoryChildrenUseCase getCategoryChildrenUseCase(CategoryReadRepository categoryReadRepository) {
		return new GetCategoryChildrenInteractor(categoryReadRepository);
	}

	@Bean
	public SearchProductsUseCase searchProductsUseCase(ProductReadRepository productReadRepository) {
		return new SearchProductsInteractor(productReadRepository);
	}

	@Bean
	public GetMonthlyHistoryUseCase getMonthlyHistoryUseCase(ProductReadRepository productReadRepository) {
		return new GetMonthlyHistoryInteractor(productReadRepository);
	}

	@Bean
	public GetProductBasicInfoUseCase getProductBasicInfoUseCase(ProductReadRepository productReadRepository) {
		return new GetProductBasicInfoInteractor(productReadRepository);
	}

	@Bean
	public GetProductDetailUseCase getProductDetailUseCase(ProductReadRepository productReadRepository) {
		return new GetProductDetailInteractor(productReadRepository);
	}

	@Bean
	public GetSellerDraftProductsUseCase getSellerDraftProductsUseCase(
			ProductReadRepository productReadRepository, SellerRepository sellerRepository) {
		return new GetSellerDraftProductsInteractor(productReadRepository, sellerRepository);
	}

	@Bean
	public GetSellerForSaleProductsUseCase getSellerForSaleProductsUseCase(
			ProductReadRepository productReadRepository, SellerRepository sellerRepository) {
		return new GetSellerForSaleProductsInteractor(productReadRepository, sellerRepository);
	}

}

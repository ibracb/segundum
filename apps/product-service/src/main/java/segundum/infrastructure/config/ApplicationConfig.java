package segundum.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import segundum.application.notificationhandlers.SaleNotificationHandler;
import segundum.application.notificationhandlers.UserNotificationHandler;
import segundum.application.notificationhandlers.interactors.SaleNotificationHandlerInteractor;
import segundum.application.notificationhandlers.interactors.UserNotificationHandlerInteractor;
import segundum.application.finders.CategoryFinder;
import segundum.application.finders.ProductFinder;
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
import segundum.application.outbound.CategoryHierarchyLoader;
import segundum.application.outbound.DomainEventPublisher;
import segundum.application.outbound.LogEmitter;
import segundum.domain.repositories.CategoryRepository;
import segundum.domain.repositories.ProductRepository;
import segundum.domain.repositories.SellerRepository;
import segundum.infrastructure.logging.Slf4jLogEmitter;
import segundum.infrastructure.persistence.mongodb.category.CategoryReadMongoRepository;
import segundum.infrastructure.persistence.mongodb.category.MongoCategoryFinder;
import segundum.infrastructure.persistence.mongodb.product.MongoProductFinder;
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
	public ProductFinder productFinder(
			ProductReadMongoRepository mongoRepository,
			MongoTemplate mongoTemplate) {
		return new MongoProductFinder(mongoRepository, mongoTemplate);
	}

	@Bean
	public CategoryFinder categoryFinder(
			CategoryReadMongoRepository mongoRepository,
			MongoTemplate mongoTemplate) {
		return new MongoCategoryFinder(mongoTemplate);
	}

	@Bean
	public CreateProductUseCase createProductUseCase(CategoryRepository categoryRepository,
			SellerRepository sellerRepository, ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		return new CreateProductInteractor(categoryRepository, sellerRepository,
				productRepository, domainEventPublisher);
	}

	@Bean
	public UpdateProductUseCase updateProductUseCase(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		return new UpdateProductInteractor(productRepository, domainEventPublisher);
	}

	@Bean
	public DiscardProductUseCase discardProductUseCase(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		return new DiscardProductInteractor(productRepository, domainEventPublisher);
	}

	@Bean
	public PutProductForSaleUseCase putProductOnSaleUseCase(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		return new PutProductForSaleInteractor(productRepository, domainEventPublisher);
	}

	@Bean
	public TakeProductDownUseCase takeProductDownUseCase(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		return new TakeProductDownInteractor(productRepository, domainEventPublisher);
	}

	@Bean
	public RemoveProductUseCase removeProductUseCase(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		return new RemoveProductInteractor(productRepository, domainEventPublisher);
	}

	@Bean
	public AssignProductPickupLocationUseCase assignProductPickupLocationUseCase(
			ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		return new AssignProductPickupLocationInteractor(productRepository, domainEventPublisher);
	}

	@Bean
	public IncrementProductViewsUseCase incrementProductViewsUseCase(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher) {
		return new IncrementProductViewsInteractor(productRepository, domainEventPublisher);
	}

	/**
	 * Instantiates the sales bounded context handler.
	 *
	 * @param productRepository the repository for writing product data
	 * @param domainEventPublisher the publisher for domain events
	 * @param logEmitter the logger
	 * @return the sales handler
	 */
	@Bean
	public SaleNotificationHandler salesHandler(ProductRepository productRepository,
			DomainEventPublisher domainEventPublisher, LogEmitter logEmitter) {
		return new SaleNotificationHandlerInteractor(productRepository, domainEventPublisher, logEmitter);
	}

	/**
	 * Instantiates the users bounded context handler.
	 *
	 * @param sellerRepository the repository for managing seller data
	 * @param logEmitter the logger
	 * @return the users handler
	 */
	@Bean
	public UserNotificationHandler usersHandler(SellerRepository sellerRepository, LogEmitter logEmitter) {
		return new UserNotificationHandlerInteractor(sellerRepository, logEmitter);
	}

	@Bean
	public LoadCategoryHierarchyUseCase loadCategoryHierarchyUseCase(CategoryHierarchyLoader categoryHierarchyLoader) {
		return new LoadCategoryHierarchyInteractor(categoryHierarchyLoader);
	}

	/**
	 * Instantiates the get root categories use case.
	 *
	 * @param categoryFinder the repository for reading category data
	 * @return the get root categories use case
	 */
	@Bean
	public GetRootCategoriesUseCase getRootCategoriesUseCase(CategoryFinder categoryFinder) {
		return new GetRootCategoriesInteractor(categoryFinder);
	}

	/**
	 * Instantiates the get category children use case.
	 *
	 * @param categoryFinder the repository for reading category data
	 * @return the get category children use case
	 */
	@Bean
	public GetCategoryChildrenUseCase getCategoryChildrenUseCase(CategoryFinder categoryFinder) {
		return new GetCategoryChildrenInteractor(categoryFinder);
	}

	@Bean
	public SearchProductsUseCase searchProductsUseCase(ProductFinder productFinder) {
		return new SearchProductsInteractor(productFinder);
	}

	@Bean
	public GetMonthlyHistoryUseCase getMonthlyHistoryUseCase(ProductFinder productFinder) {
		return new GetMonthlyHistoryInteractor(productFinder);
	}

	@Bean
	public GetProductBasicInfoUseCase getProductBasicInfoUseCase(ProductFinder productFinder) {
		return new GetProductBasicInfoInteractor(productFinder);
	}

	@Bean
	public GetProductDetailUseCase getProductDetailUseCase(ProductFinder productFinder) {
		return new GetProductDetailInteractor(productFinder);
	}

	@Bean
	public GetSellerDraftProductsUseCase getSellerDraftProductsUseCase(
			ProductFinder productFinder, SellerRepository sellerRepository) {
		return new GetSellerDraftProductsInteractor(productFinder, sellerRepository);
	}

	@Bean
	public GetSellerForSaleProductsUseCase getSellerForSaleProductsUseCase(
			ProductFinder productFinder, SellerRepository sellerRepository) {
		return new GetSellerForSaleProductsInteractor(productFinder, sellerRepository);
	}

}

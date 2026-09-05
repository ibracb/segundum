package segundum.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import segundum.application.gateways.GetProductBasicInfo;
import segundum.application.gateways.GetPurchaser;
import segundum.application.gateways.GetSeller;
import segundum.application.finders.SaleFinder;
import segundum.application.usecases.CancelSaleByPurchaserUseCase;
import segundum.application.usecases.CancelSaleBySellerUseCase;
import segundum.application.usecases.CompleteSaleUseCase;
import segundum.application.usecases.SearchSalesUseCase;
import segundum.application.usecases.GetSalesByPurchaserUseCase;
import segundum.application.usecases.GetSalesBySellerUseCase;
import segundum.application.usecases.ProposeSaleUseCase;
import segundum.application.usecases.RejectSaleUseCase;
import segundum.application.usecases.ReserveSaleUseCase;
import segundum.application.usecases.interactors.CancelSaleByPurchaserInteractor;
import segundum.application.usecases.interactors.CancelSaleBySellerInteractor;
import segundum.application.usecases.interactors.CompleteSaleInteractor;
import segundum.application.usecases.interactors.GetSalesByPurchaserInteractor;
import segundum.application.usecases.interactors.SearchSalesInteractor;
import segundum.application.usecases.interactors.GetSalesBySellerInteractor;
import segundum.application.usecases.interactors.ProposeSaleInteractor;
import segundum.application.usecases.interactors.RejectSaleInteractor;
import segundum.application.usecases.interactors.ReserveSaleInteractor;
import segundum.application.outbound.DomainEventPublisher;
import segundum.application.outbound.EventStore;
import segundum.infrastructure.persistence.mongodb.sale.MongoSaleFinder;

/**
 * Represents the application configuration for the sale service.
 */
@Configuration
public class ApplicationConfig {

    /**
     * Creates the sale read repository bean.
     *
     * @param mongoTemplate the Mongo template
     * @return the sale read repository
     */
    @Bean
    public SaleFinder saleFinder(MongoTemplate mongoTemplate) {
        return new MongoSaleFinder(mongoTemplate);
    }

    /**
     * Creates the reserve sale use case bean.
     *
     * @param eventStore     the event store
     * @param eventPublisher the domain event publisher
     * @return the reserve sale use case
     */
    @Bean
    public ReserveSaleUseCase reserveSaleUseCase(EventStore eventStore, DomainEventPublisher eventPublisher) {
        return new ReserveSaleInteractor(eventStore, eventPublisher);
    }

    /**
     * Creates the complete sale use case bean.
     *
     * @param eventStore     the event store
     * @param eventPublisher the domain event publisher
     * @return the complete sale use case
     */
    @Bean
    public CompleteSaleUseCase completeSaleUseCase(EventStore eventStore, DomainEventPublisher eventPublisher) {
        return new CompleteSaleInteractor(eventStore, eventPublisher);
    }

    /**
     * Creates the reject sale use case bean.
     *
     * @param eventStore     the event store
     * @param eventPublisher the domain event publisher
     * @return the reject sale use case
     */
    @Bean
    public RejectSaleUseCase rejectSaleUseCase(EventStore eventStore, DomainEventPublisher eventPublisher) {
        return new RejectSaleInteractor(eventStore, eventPublisher);
    }

    /**
     * Creates the cancel sale by purchaser use case bean.
     *
     * @param eventStore     the event store
     * @param eventPublisher the domain event publisher
     * @return the cancel sale by purchaser use case
     */
    @Bean
    public CancelSaleByPurchaserUseCase cancelSaleByPurchaserUseCase(EventStore eventStore,
            DomainEventPublisher eventPublisher) {
        return new CancelSaleByPurchaserInteractor(eventStore, eventPublisher);
    }

    /**
     * Creates the cancel sale by seller use case bean.
     *
     * @param eventStore     the event store
     * @param eventPublisher the domain event publisher
     * @return the cancel sale by seller use case
     */
    @Bean
    public CancelSaleBySellerUseCase cancelSaleBySellerUseCase(EventStore eventStore,
            DomainEventPublisher eventPublisher) {
        return new CancelSaleBySellerInteractor(eventStore, eventPublisher);
    }

    /**
     * Creates the propose sale use case bean.
     *
     * @param productGateway  the product basic info gateway
     * @param sellerGateway   the seller gateway
     * @param purchaserGateway the purchaser gateway
     * @param eventStore      the event store
     * @param eventPublisher  the domain event publisher
     * @return the propose sale use case
     */
    @Bean
    public ProposeSaleUseCase proposeSaleUseCase(GetProductBasicInfo productGateway, GetSeller sellerGateway,
            GetPurchaser purchaserGateway, EventStore eventStore, DomainEventPublisher eventPublisher) {
        return new ProposeSaleInteractor(productGateway, sellerGateway, purchaserGateway, eventStore, eventPublisher);
    }

    /**
     * Creates the get sales by purchaser use case bean.
     *
     * @param saleFinder the sale read repository
     * @return the get sales by purchaser use case
     */
    @Bean
    public GetSalesByPurchaserUseCase getSalesByPurchaserUseCase(SaleFinder saleFinder) {
        return new GetSalesByPurchaserInteractor(saleFinder);
    }

    /**
     * Creates the get sales by seller use case bean.
     *
     * @param saleFinder the sale read repository
     * @return the get sales by seller use case
     */
    @Bean
    public GetSalesBySellerUseCase getSalesBySellerUseCase(SaleFinder saleFinder) {
        return new GetSalesBySellerInteractor(saleFinder);
    }

    /**
     * Creates the search sales use case bean.
     *
     * @param saleFinder the sale read repository
     * @return the search sales use case
     */
    @Bean
    public SearchSalesUseCase searchSalesUseCase(SaleFinder saleFinder) {
        return new SearchSalesInteractor(saleFinder);
    }

}

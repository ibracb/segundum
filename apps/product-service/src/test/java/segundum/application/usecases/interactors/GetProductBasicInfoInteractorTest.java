package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetProductBasicInfoQuery;
import segundum.application.readmodels.product.PickupLocationReadModel;
import segundum.application.readmodels.product.ProductBasicInfo;
import segundum.application.usecases.GetProductBasicInfoUseCase;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.persistence.fakes.finders.FakeProductFinder;

class GetProductBasicInfoInteractorTest {

    private FakeProductFinder repository;
    private GetProductBasicInfoUseCase interactor;

    @BeforeEach
    void setUp() {
        repository = new FakeProductFinder();
        interactor = new GetProductBasicInfoInteractor(repository);
    }

    @Test
    void shouldReturnProductBasicInfo() {
        ProductId productId = ProductId.generate();
        PickupLocationReadModel pickup = new PickupLocationReadModel("Main entrance", 40.4168, -3.7038);
        ProductBasicInfo info = new ProductBasicInfo(
                productId.getValue().toString(), "iPhone 12", 299.99, pickup, "seller-123", "FOR_SALE");
        repository.setProductBasicInfo(Optional.of(info));

        Optional<ProductBasicInfo> result = interactor.execute(new GetProductBasicInfoQuery(productId));

        assertTrue(result.isPresent());
        assertEquals("iPhone 12", result.get().getTitle());
        assertEquals(299.99, result.get().getPrice());
        assertEquals("Main entrance", result.get().getPickupLocation().getDescription());
        assertEquals("seller-123", result.get().getSellerId());
        assertEquals("FOR_SALE", result.get().getSaleStatus());
    }

    @Test
    void shouldReturnEmptyWhenNotFound() {
        ProductId productId = ProductId.generate();
        repository.setProductBasicInfo(Optional.empty());

        Optional<ProductBasicInfo> result = interactor.execute(new GetProductBasicInfoQuery(productId));

        assertTrue(result.isEmpty());
    }

}

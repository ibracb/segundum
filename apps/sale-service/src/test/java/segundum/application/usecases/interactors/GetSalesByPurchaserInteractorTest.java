package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetSalesByPurchaserQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.usecases.GetSalesByPurchaserUseCase;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;
import segundum.infrastructure.persistence.fakes.FakeSaleReadRepository;

class GetSalesByPurchaserInteractorTest {

	private FakeSaleReadRepository repository;
	private GetSalesByPurchaserUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeSaleReadRepository();
		interactor = new GetSalesByPurchaserInteractor(repository);
	}

	@Test
	void shouldDelegateToRepositoryWithQueryParams() {
		PurchaserId purchaserId = PurchaserId.fromUUID(UUID.fromString("22222222-2222-2222-2222-222222222222"));
		Page<SaleAsPurchaserReadModel> expected = new Page<>(List.of(), 0, 3, 15);
		repository.setPurchaserPage(expected);

		Page<SaleAsPurchaserReadModel> result = interactor.execute(
				new GetSalesByPurchaserQuery(purchaserId, OrderStatus.PENDING, 3, 15));

		assertSame(expected, result);
		assertEquals(purchaserId, repository.getLastPurchaserId());
		assertEquals(OrderStatus.PENDING, repository.getLastStatus());
		assertEquals(3, repository.getLastPageNumber());
		assertEquals(15, repository.getLastPageSize());
	}

	@Test
	void shouldDelegateWithNullStatus() {
		PurchaserId purchaserId = PurchaserId.fromUUID(UUID.fromString("22222222-2222-2222-2222-222222222222"));

		interactor.execute(new GetSalesByPurchaserQuery(purchaserId, null, 0, 20));

		assertEquals(purchaserId, repository.getLastPurchaserId());
		assertEquals(null, repository.getLastStatus());
	}

}

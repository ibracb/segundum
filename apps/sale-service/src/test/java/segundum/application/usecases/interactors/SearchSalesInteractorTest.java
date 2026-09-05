package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.SearchSalesQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.sale.SaleDetailReadModel;
import segundum.application.usecases.SearchSalesUseCase;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.persistence.fakes.finders.FakeSaleFinder;

class SearchSalesInteractorTest {

	private FakeSaleFinder repository;
	private SearchSalesUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeSaleFinder();
		interactor = new SearchSalesInteractor(repository);
	}

	@Test
	void shouldDelegateToRepositoryWithAllParams() {
		PurchaserId purchaserId = PurchaserId.fromUUID(UUID.fromString("22222222-2222-2222-2222-222222222222"));
		SellerId sellerId = SellerId.fromUUID(UUID.fromString("33333333-3333-3333-3333-333333333333"));
		Page<SaleDetailReadModel> expected = new Page<>(List.of(), 0, 3, 15);
		repository.setDetailPage(expected);

		Page<SaleDetailReadModel> result = interactor.execute(
				new SearchSalesQuery(purchaserId, sellerId, OrderStatus.PENDING, 3, 15));

		assertSame(expected, result);
		assertEquals(purchaserId, repository.getLastPurchaserId());
		assertEquals(sellerId, repository.getLastSellerId());
		assertEquals(OrderStatus.PENDING, repository.getLastStatus());
		assertEquals(3, repository.getLastPageNumber());
		assertEquals(15, repository.getLastPageSize());
	}

	@Test
	void shouldDelegateWithAllNullFilters() {
		Page<SaleDetailReadModel> expected = new Page<>(List.of(), 0, 0, 20);
		repository.setDetailPage(expected);

		Page<SaleDetailReadModel> result = interactor.execute(
				new SearchSalesQuery(null, null, null, 0, 20));

		assertSame(expected, result);
		assertNull(repository.getLastPurchaserId());
		assertNull(repository.getLastSellerId());
		assertNull(repository.getLastStatus());
	}

	@Test
	void shouldDelegateWithOnlyPurchaserId() {
		PurchaserId purchaserId = PurchaserId.fromUUID(UUID.fromString("22222222-2222-2222-2222-222222222222"));

		interactor.execute(new SearchSalesQuery(purchaserId, null, null, 0, 20));

		assertEquals(purchaserId, repository.getLastPurchaserId());
		assertNull(repository.getLastSellerId());
		assertNull(repository.getLastStatus());
	}

	@Test
	void shouldDelegateWithOnlySellerId() {
		SellerId sellerId = SellerId.fromUUID(UUID.fromString("33333333-3333-3333-3333-333333333333"));

		interactor.execute(new SearchSalesQuery(null, sellerId, null, 0, 20));

		assertNull(repository.getLastPurchaserId());
		assertEquals(sellerId, repository.getLastSellerId());
		assertNull(repository.getLastStatus());
	}

}

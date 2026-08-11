package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetSalesBySellerQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.usecases.GetSalesBySellerUseCase;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.persistence.fakes.FakeSaleReadRepository;

class GetSalesBySellerInteractorTest {

	private FakeSaleReadRepository repository;
	private GetSalesBySellerUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeSaleReadRepository();
		interactor = new GetSalesBySellerInteractor(repository);
	}

	@Test
	void shouldDelegateToRepositoryWithQueryParams() {
		SellerId sellerId = SellerId.fromUUID(UUID.fromString("11111111-1111-1111-1111-111111111111"));
		Page<SaleAsSellerReadModel> expected = new Page<>(List.of(), 0, 2, 10);
		repository.setSellerPage(expected);

		Page<SaleAsSellerReadModel> result = interactor.execute(
				new GetSalesBySellerQuery(sellerId, OrderStatus.COMPLETED, 2, 10));

		assertSame(expected, result);
		assertEquals(sellerId, repository.getLastSellerId());
		assertEquals(OrderStatus.COMPLETED, repository.getLastStatus());
		assertEquals(2, repository.getLastPageNumber());
		assertEquals(10, repository.getLastPageSize());
	}

	@Test
	void shouldDelegateWithNullStatus() {
		SellerId sellerId = SellerId.fromUUID(UUID.fromString("11111111-1111-1111-1111-111111111111"));

		interactor.execute(new GetSalesBySellerQuery(sellerId, null, 0, 20));

		assertEquals(sellerId, repository.getLastSellerId());
		assertEquals(null, repository.getLastStatus());
		assertEquals(0, repository.getLastPageNumber());
		assertEquals(20, repository.getLastPageSize());
	}

}

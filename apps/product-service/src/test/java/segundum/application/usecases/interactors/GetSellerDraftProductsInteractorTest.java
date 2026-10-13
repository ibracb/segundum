package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetSellerDraftProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.application.usecases.GetSellerDraftProductsUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.persistence.fakes.finders.FakeProductFinder;
import segundum.infrastructure.persistence.fakes.finders.FakeSellerFinder;

class GetSellerDraftProductsInteractorTest {

	private FakeProductFinder productFinder;
	private FakeSellerFinder sellerFinder;
	private GetSellerDraftProductsUseCase interactor;
	private SellerId activeSellerId;

	@BeforeEach
	void setUp() {
		productFinder = new FakeProductFinder();
		sellerFinder = new FakeSellerFinder();
		interactor = new GetSellerDraftProductsInteractor(productFinder, sellerFinder);

		activeSellerId = SellerId.fromUUID(UUID.randomUUID());
		sellerFinder.put(activeSeller());
	}

	private SellerReadModel activeSeller() {
		return new SellerReadModel(
				activeSellerId.getValue().toString(),
				"Juan",
				"Pérez",
				"juan@email.com",
				true);
	}

	@Test
	void shouldDelegateToFinder() {
		Page<SellerProduct> expectedResult = new Page<SellerProduct>(Collections.emptyList(), 0, 0, 20);
		productFinder.setSellerDraftsResult(expectedResult);

		Page<SellerProduct> result = interactor.execute(new GetSellerDraftProductsQuery(activeSellerId, 0, 20));

		assertEquals(expectedResult, result);
	}

	@Test
	void shouldThrowWhenSellerNotFound() {
		SellerId unknownId = SellerId.fromUUID(UUID.randomUUID());
		assertThrows(EntityNotFoundException.class,
				() -> interactor.execute(new GetSellerDraftProductsQuery(unknownId, 0, 20)));
	}

	@Test
	void shouldThrowWhenSellerIsInactive() {
		sellerFinder.put(new SellerReadModel(
				activeSellerId.getValue().toString(),
				"Juan",
				"Pérez",
				"juan@email.com",
				false));

		assertThrows(SellerNotActiveException.class,
				() -> interactor.execute(new GetSellerDraftProductsQuery(activeSellerId, 0, 20)));
	}
}

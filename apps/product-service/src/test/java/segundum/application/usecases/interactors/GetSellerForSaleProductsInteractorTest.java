package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetSellerForSaleProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.usecases.GetSellerForSaleProductsUseCase;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductReadRepository;

import java.util.UUID;

class GetSellerForSaleProductsInteractorTest {

	private FakeProductReadRepository repository;
	private GetSellerForSaleProductsUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeProductReadRepository();
		interactor = new GetSellerForSaleProductsInteractor(repository);
	}

	@Test
	void shouldDelegateToRepository() {
		SellerId sellerId = SellerId.fromUUID(UUID.randomUUID());
		Page<SellerProduct> expectedResult = new Page<SellerProduct>(Collections.emptyList(), 0, 0, 20);
		repository.setSellerForSaleResult(expectedResult);

		Page<SellerProduct> result = interactor.execute(new GetSellerForSaleProductsQuery(sellerId, 0, 20));

		assertEquals(expectedResult, result);
	}
}

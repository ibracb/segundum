package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetSellerForSaleProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.usecases.GetSellerForSaleProductsUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.exceptions.seller.status.SellerNotActiveException;
import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductReadRepository;
import segundum.infrastructure.persistence.fakes.repositories.FakeSellerRepository;

class GetSellerForSaleProductsInteractorTest {

	private FakeProductReadRepository productReadRepository;
	private FakeSellerRepository sellerRepository;
	private GetSellerForSaleProductsUseCase interactor;
	private SellerId activeSellerId;

	@BeforeEach
	void setUp() {
		productReadRepository = new FakeProductReadRepository();
		sellerRepository = new FakeSellerRepository();
		interactor = new GetSellerForSaleProductsInteractor(productReadRepository, sellerRepository);

		activeSellerId = SellerId.fromUUID(UUID.randomUUID());
		Seller seller = SellerFactory.create(activeSellerId,
				new Name("Juan"),
				new Surname("Pérez"),
				new Email("juan@email.com"));
		sellerRepository.create(seller);
	}

	@Test
	void shouldDelegateToRepository() {
		Page<SellerProduct> expectedResult = new Page<SellerProduct>(Collections.emptyList(), 0, 0, 20);
		productReadRepository.setSellerForSaleResult(expectedResult);

		Page<SellerProduct> result = interactor.execute(new GetSellerForSaleProductsQuery(activeSellerId, 0, 20));

		assertEquals(expectedResult, result);
	}

	@Test
	void shouldThrowWhenSellerNotFound() {
		SellerId unknownId = SellerId.fromUUID(UUID.randomUUID());
		assertThrows(EntityNotFoundException.class,
				() -> interactor.execute(new GetSellerForSaleProductsQuery(unknownId, 0, 20)));
	}

	@Test
	void shouldThrowWhenSellerIsInactive() {
		Seller inactiveSeller = sellerRepository.findById(activeSellerId).get();
		inactiveSeller.deactivate();
		sellerRepository.create(inactiveSeller);

		assertThrows(SellerNotActiveException.class,
				() -> interactor.execute(new GetSellerForSaleProductsQuery(activeSellerId, 0, 20)));
	}
}

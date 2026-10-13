package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetSalePartiesQuery;
import segundum.application.readmodels.sale.SalePartiesReadModel;
import segundum.application.usecases.GetSalePartiesUseCase;
import segundum.domain.models.sale.SaleId;
import segundum.infrastructure.persistence.fakes.finders.FakeSaleFinder;

class GetSalePartiesInteractorTest {

	private FakeSaleFinder repository;
	private GetSalePartiesUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeSaleFinder();
		interactor = new GetSalePartiesInteractor(repository);
	}

	@Test
	void shouldDelegateToRepositoryWithSaleId() {
		SaleId saleId = SaleId.fromString("11111111-1111-1111-1111-111111111111");
		SalePartiesReadModel expected = new SalePartiesReadModel(
				"11111111-1111-1111-1111-111111111111",
				"22222222-2222-2222-2222-222222222222",
				"33333333-3333-3333-3333-333333333333",
				"COMPLETED");
		repository.setParties(Optional.of(expected));

		Optional<SalePartiesReadModel> result = interactor.execute(new GetSalePartiesQuery(saleId));

		assertSame(expected, result.get());
		assertEquals(saleId, repository.getLastSaleId());
	}

	@Test
	void shouldReturnEmptyWhenSaleDoesNotExist() {
		SaleId saleId = SaleId.generate();

		Optional<SalePartiesReadModel> result = interactor.execute(new GetSalePartiesQuery(saleId));

		assertTrue(result.isEmpty());
		assertEquals(saleId, repository.getLastSaleId());
	}

}

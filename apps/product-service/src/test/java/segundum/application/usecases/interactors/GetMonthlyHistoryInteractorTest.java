package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetMonthlyHistoryQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.usecases.GetMonthlyHistoryUseCase;
import segundum.infrastructure.persistence.fakes.finders.FakeProductFinder;

class GetMonthlyHistoryInteractorTest {

	private FakeProductFinder repository;
	private GetMonthlyHistoryUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeProductFinder();
		interactor = new GetMonthlyHistoryInteractor(repository);
	}

	@Test
	void shouldDelegateToRepository() {
		Page<ProductSummary> expectedResult = new Page<ProductSummary>(Collections.emptyList(), 0, 0, 20);
		repository.setMonthlyHistoryResult(expectedResult);

		Page<ProductSummary> result = interactor.execute(new GetMonthlyHistoryQuery(6, 2025, 0, 20));

		assertEquals(expectedResult, result);
	}
}

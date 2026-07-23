package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.SearchProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.usecases.SearchProductsUseCase;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Price;
import segundum.infrastructure.persistence.fakes.repositories.FakeProductReadRepository;

class SearchProductsInteractorTest {

	private FakeProductReadRepository repository;
	private SearchProductsUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeProductReadRepository();
		interactor = new SearchProductsInteractor(repository);
	}

	@Test
	void shouldDelegateToRepository() {
		CategoryId categoryId = CategoryId.fromString("1");
		Page<ProductSearchResult> expectedResult = new Page<ProductSearchResult>(Collections.emptyList(), 0, 0, 20);
		repository.setSearchResult(expectedResult);

		Page<ProductSearchResult> result = interactor.execute(new SearchProductsQuery(
				categoryId, "iphone", ConditionStatus.NEW, new Price(100), 0, 20));

		assertEquals(expectedResult, result);
	}
}

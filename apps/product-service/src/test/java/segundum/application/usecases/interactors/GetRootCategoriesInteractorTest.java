package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetRootCategoriesQuery;
import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.usecases.GetRootCategoriesUseCase;
import segundum.infrastructure.persistence.fakes.finders.FakeCategoryFinder;

class GetRootCategoriesInteractorTest {

	private FakeCategoryFinder repository;
	private GetRootCategoriesUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeCategoryFinder();
		interactor = new GetRootCategoriesInteractor(repository);
	}

	@Test
	void shouldReturnRootCategories() {
		CategoryReadModel cat1 = new CategoryReadModel("1", "Electrónica", "|1|", null, null);
		CategoryReadModel cat2 = new CategoryReadModel("2", "Hogar", "|2|", null, null);
		repository.setRootCategories(Arrays.asList(cat1, cat2));

		List<CategoryReadModel> result = interactor.execute(new GetRootCategoriesQuery());

		assertEquals(2, result.size());
		assertEquals("Electrónica", result.get(0).getName());
	}

	@Test
	void shouldReturnEmptyListWhenNoCategories() {
		repository.setRootCategories(Collections.emptyList());

		List<CategoryReadModel> result = interactor.execute(new GetRootCategoriesQuery());

		assertTrue(result.isEmpty());
	}
}

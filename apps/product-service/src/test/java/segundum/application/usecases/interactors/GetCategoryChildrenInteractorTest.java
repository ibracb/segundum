package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.queries.GetCategoryChildrenQuery;
import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.usecases.GetCategoryChildrenUseCase;
import segundum.domain.models.category.CategoryId;
import segundum.infrastructure.persistence.fakes.finders.FakeCategoryFinder;

class GetCategoryChildrenInteractorTest {

	private FakeCategoryFinder repository;
	private GetCategoryChildrenUseCase interactor;

	@BeforeEach
	void setUp() {
		repository = new FakeCategoryFinder();
		interactor = new GetCategoryChildrenInteractor(repository);
	}

	@Test
	void shouldReturnChildren() {
		CategoryId parentId = CategoryId.fromString("1");
		CategoryReadModel child1 = new CategoryReadModel("10", "Móviles", "|1|10|", null, "1");
		CategoryReadModel child2 = new CategoryReadModel("11", "Portátiles", "|1|11|", null, "1");
		repository.setChildren(parentId, Arrays.asList(child1, child2));

		List<CategoryReadModel> result = interactor.execute(new GetCategoryChildrenQuery(parentId));

		assertEquals(2, result.size());
		assertEquals("Móviles", result.get(0).getName());
	}

	@Test
	void shouldReturnEmptyListWhenNoChildren() {
		CategoryId parentId = CategoryId.fromString("1");
		repository.setChildren(parentId, Collections.emptyList());

		List<CategoryReadModel> result = interactor.execute(new GetCategoryChildrenQuery(parentId));

		assertTrue(result.isEmpty());
	}
}

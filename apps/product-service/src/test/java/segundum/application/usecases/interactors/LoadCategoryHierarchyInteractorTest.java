package segundum.application.usecases.interactors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import segundum.application.commands.LoadCategoryHierarchyCommand;
import segundum.application.usecases.LoadCategoryHierarchyUseCase;
import segundum.infrastructure.persistence.fakes.FakeCategoryHierarchyLoader;

class LoadCategoryHierarchyInteractorTest {

	private FakeCategoryHierarchyLoader loader;
	private LoadCategoryHierarchyUseCase interactor;

	@BeforeEach
	void setUp() {
		loader = new FakeCategoryHierarchyLoader();
		interactor = new LoadCategoryHierarchyInteractor(loader);
	}

	@Test
	void shouldCallLoaderWithSource() {
		LoadCategoryHierarchyCommand command = new LoadCategoryHierarchyCommand("/data/categories.xml");

		interactor.execute(command);

		assertEquals("/data/categories.xml", loader.getLastSource());
	}
}

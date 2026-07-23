package segundum.application.usecases.interactors;

import segundum.application.commands.LoadCategoryHierarchyCommand;
import segundum.application.usecases.LoadCategoryHierarchyUseCase;
import segundum.domain.outbound.CategoryHierarchyLoader;

/**
 * Represents the interactor for loading a category hierarchy.
 */
public class LoadCategoryHierarchyInteractor implements LoadCategoryHierarchyUseCase {

	private final CategoryHierarchyLoader categoryHierarchyLoader;

	public LoadCategoryHierarchyInteractor(CategoryHierarchyLoader categoryHierarchyLoader) {
		this.categoryHierarchyLoader = categoryHierarchyLoader;
	}

	@Override
	public void execute(LoadCategoryHierarchyCommand command) {
		categoryHierarchyLoader.load(command.getSource());
	}

}

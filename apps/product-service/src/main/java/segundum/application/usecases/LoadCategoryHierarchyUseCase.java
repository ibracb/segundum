package segundum.application.usecases;

import segundum.application.commands.LoadCategoryHierarchyCommand;

/**
 * Represents the use case for loading a category hierarchy.
 */
public interface LoadCategoryHierarchyUseCase {

	/**
	 * Executes the use case to load a category hierarchy.
	 *
	 * @param command the command containing the source
	 */
	void execute(LoadCategoryHierarchyCommand command);

}

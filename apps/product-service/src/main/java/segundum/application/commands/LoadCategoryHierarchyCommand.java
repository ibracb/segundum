package segundum.application.commands;

/**
 * Represents a command to load a category hierarchy from a source.
 */
public class LoadCategoryHierarchyCommand {

	/**
	 * The source to load the hierarchy from (e.g., file path).
	 */
	private final String source;

	/**
	 * Constructs a new LoadCategoryHierarchyCommand with the given source.
	 *
	 * @param source the source to load the hierarchy from
	 */
	public LoadCategoryHierarchyCommand(String source) {
		this.source = source;
	}

	/**
	 * Returns the source to load the hierarchy from.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

}

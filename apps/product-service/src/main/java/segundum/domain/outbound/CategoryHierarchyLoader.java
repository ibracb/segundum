package segundum.domain.outbound;

/**
 * Domain service interface for loading category hierarchies.
 * <p>
 * Defines the contract for loading category hierarchies from external sources.
 * Implementations reside in the infrastructure layer.
 * </p>
 */
public interface CategoryHierarchyLoader {

	/**
	 * Loads a category hierarchy from the given source.
	 *
	 * @param source the source to load the hierarchy from (e.g., file path)
	 */
	void load(String source);

}

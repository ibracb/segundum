package segundum.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import segundum.application.commands.LoadCategoryHierarchyCommand;
import segundum.application.usecases.LoadCategoryHierarchyUseCase;

/**
 * Loads the category hierarchy from XML files at application startup.
 */
@Component
public class CategoryStartupLoader implements CommandLineRunner {

	/**
	 * The logger used to log messages.
	 */
	private static final Logger log = LoggerFactory.getLogger(CategoryStartupLoader.class);

	/**
	 * The use case used to load the category hierarchy.
	 */
	private final LoadCategoryHierarchyUseCase loadCategoryHierarchyUseCase;
	/**
	 * The resolver used to locate category XML resources.
	 */
	private final ResourcePatternResolver resourcePatternResolver;

	/**
	 * Constructs a new CategoryStartupLoader with the given dependencies.
	 *
	 * @param loadCategoryHierarchyUseCase the load category hierarchy use case
	 * @param resourcePatternResolver the resource pattern resolver
	 */
	public CategoryStartupLoader(LoadCategoryHierarchyUseCase loadCategoryHierarchyUseCase,
			ResourcePatternResolver resourcePatternResolver) {
		this.loadCategoryHierarchyUseCase = loadCategoryHierarchyUseCase;
		this.resourcePatternResolver = resourcePatternResolver;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting category hierarchy loading from XML files...");
		Resource[] xmlResources = resourcePatternResolver.getResources("classpath:categories/*.xml");
		log.info("Found {} category XML files", xmlResources.length);
		for (Resource xmlResource : xmlResources) {
			String filename = xmlResource.getFilename();
			log.info("Loading categories from: {}", filename);
			LoadCategoryHierarchyCommand command = new LoadCategoryHierarchyCommand("classpath:categories/" + filename);
			loadCategoryHierarchyUseCase.execute(command);
			log.info("Loaded categories from: {}", filename);
		}
		log.info("Category hierarchy loading completed. {} files processed.", xmlResources.length);
	}
}

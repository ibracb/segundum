package segundum.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import segundum.application.commands.LoadCategoryHierarchyCommand;
import segundum.application.usecases.LoadCategoryHierarchyUseCase;

@Component
public class CategoryStartupLoader implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(CategoryStartupLoader.class);

	private final LoadCategoryHierarchyUseCase loadCategoryHierarchyUseCase;
	private final ResourcePatternResolver resourcePatternResolver;

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

package segundum.infrastructure.rest.category.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.queries.GetRootCategoriesQuery;
import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.usecases.GetRootCategoriesUseCase;
import segundum.infrastructure.rest.category.api.GetRootCategoriesApi;
import segundum.infrastructure.rest.category.assemblers.CategoryModelAssembler;
import segundum.infrastructure.rest.category.responses.CategoryResponse;

@RestController
/**
 * Represents the controller for retrieving the root categories.
 */
public class GetRootCategoriesController implements GetRootCategoriesApi {

	/**
	 * The use case used to retrieve the root categories.
	 */
	private final GetRootCategoriesUseCase getRootCategoriesUseCase;
	/**
	 * The assembler used to build category models.
	 */
	private final CategoryModelAssembler assembler;

	/**
	 * Constructs a new GetRootCategoriesController with the given dependencies.
	 *
	 * @param getRootCategoriesUseCase the get root categories use case
	 * @param assembler the category model assembler
	 */
	public GetRootCategoriesController(
			GetRootCategoriesUseCase getRootCategoriesUseCase,
			CategoryModelAssembler assembler) {
		this.getRootCategoriesUseCase = getRootCategoriesUseCase;
		this.assembler = assembler;
	}

	@Override
	public ResponseEntity<CollectionModel<EntityModel<CategoryResponse>>> getRootCategories() {
		List<CategoryReadModel> categories = getRootCategoriesUseCase.execute(new GetRootCategoriesQuery());
		List<EntityModel<CategoryResponse>> models = categories.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		CollectionModel<EntityModel<CategoryResponse>> collection = CollectionModel.of(models,
				linkTo(methodOn(GetRootCategoriesController.class).getRootCategories()).withSelfRel());
		return ResponseEntity.ok(collection);
	}

}

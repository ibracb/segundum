package segundum.infrastructure.rest.category.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.queries.GetCategoryChildrenQuery;
import segundum.application.readmodels.category.CategoryReadModel;
import segundum.application.usecases.GetCategoryChildrenUseCase;
import segundum.domain.models.category.CategoryId;
import segundum.infrastructure.rest.category.api.GetCategoryChildrenApi;
import segundum.infrastructure.rest.category.assemblers.CategoryModelAssembler;
import segundum.infrastructure.rest.category.responses.CategoryResponse;

@RestController
/**
 * Represents the controller for retrieving the children of a category.
 */
public class GetCategoryChildrenController implements GetCategoryChildrenApi {

	/**
	 * The use case used to retrieve the children of a category.
	 */
	private final GetCategoryChildrenUseCase getCategoryChildrenUseCase;
	/**
	 * The assembler used to build category models.
	 */
	private final CategoryModelAssembler assembler;

	/**
	 * Constructs a new GetCategoryChildrenController with the given dependencies.
	 *
	 * @param getCategoryChildrenUseCase the get category children use case
	 * @param assembler the category model assembler
	 */
	public GetCategoryChildrenController(
			GetCategoryChildrenUseCase getCategoryChildrenUseCase,
			CategoryModelAssembler assembler) {
		this.getCategoryChildrenUseCase = getCategoryChildrenUseCase;
		this.assembler = assembler;
	}

	@Override
	public ResponseEntity<CollectionModel<EntityModel<CategoryResponse>>> getCategoryChildren(String id) {
		CategoryId categoryId = CategoryId.fromString(id);
		List<CategoryReadModel> children = getCategoryChildrenUseCase.execute(
				new GetCategoryChildrenQuery(categoryId));
		List<EntityModel<CategoryResponse>> models = children.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		CollectionModel<EntityModel<CategoryResponse>> collection = CollectionModel.of(models,
				linkTo(methodOn(GetCategoryChildrenController.class).getCategoryChildren(id)).withSelfRel());
		return ResponseEntity.ok(collection);
	}

}

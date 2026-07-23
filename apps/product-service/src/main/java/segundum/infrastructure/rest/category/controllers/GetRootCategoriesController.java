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
public class GetRootCategoriesController implements GetRootCategoriesApi {

	private final GetRootCategoriesUseCase getRootCategoriesUseCase;
	private final CategoryModelAssembler assembler;

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

package segundum.infrastructure.rest.category.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import segundum.application.readmodels.category.CategoryReadModel;
import segundum.infrastructure.rest.category.controllers.GetCategoryChildrenController;
import segundum.infrastructure.rest.category.responses.CategoryResponse;

/**
 * Assembler that converts CategoryReadModel to HATEOAS EntityModel responses.
 */
@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<CategoryReadModel, EntityModel<CategoryResponse>> {

	@Override
	public EntityModel<CategoryResponse> toModel(CategoryReadModel category) {
		CategoryResponse response = new CategoryResponse(
				category.getCategoryId(),
				category.getName(),
				category.getPath(),
				category.getDescription(),
				category.getParentCategoryId());
		EntityModel<CategoryResponse> model = EntityModel.of(response);
		model.add(linkTo(methodOn(GetCategoryChildrenController.class)
				.getCategoryChildren(category.getCategoryId()))
				.withRel("children"));
		return model;
	}

}

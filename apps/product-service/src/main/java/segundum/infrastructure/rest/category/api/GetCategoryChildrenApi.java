package segundum.infrastructure.rest.category.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import segundum.infrastructure.rest.category.responses.CategoryResponse;

@Tag(name = "Categories", description = "Category management endpoints")
@RequestMapping("/categories")
public interface GetCategoryChildrenApi {

	@Operation(summary = "Get category children", description = "Returns all direct subcategories of a given category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Children retrieved successfully",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = CategoryResponse.class))),
			@ApiResponse(responseCode = "404", description = "Category not found",
					content = @Content)
	})
	@GetMapping(value = "/{id}/children", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<CollectionModel<EntityModel<CategoryResponse>>> getCategoryChildren(
			@Parameter(description = "The category identifier") @PathVariable("id") String id);

}

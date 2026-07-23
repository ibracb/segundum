package segundum.infrastructure.rest.product.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.queries.SearchProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSearchResult;
import segundum.application.usecases.SearchProductsUseCase;
import segundum.domain.models.category.CategoryId;
import segundum.domain.models.product.ConditionStatus;
import segundum.domain.models.product.Price;
import segundum.infrastructure.rest.product.api.SearchProductsApi;
import segundum.infrastructure.rest.product.assemblers.ProductModelAssembler;
import segundum.infrastructure.rest.product.responses.ProductSearchResultResponse;

@RestController
public class SearchProductsController implements SearchProductsApi {

	private final SearchProductsUseCase useCase;
	private final ProductModelAssembler assembler;

	public SearchProductsController(SearchProductsUseCase useCase,
			ProductModelAssembler assembler) {
		this.useCase = useCase;
		this.assembler = assembler;
	}

	@Override
	public ResponseEntity<PagedModel<EntityModel<ProductSearchResultResponse>>> search(
			String categoryId, String description, String condition, Double maxPrice,
			Pageable pageable) {
		SearchProductsQuery query = new SearchProductsQuery(
				categoryId != null ? CategoryId.fromString(categoryId) : null,
				description,
				condition != null ? ConditionStatus.valueOf(condition) : null,
				maxPrice != null ? new Price(maxPrice) : null,
				pageable.getPageNumber(), pageable.getPageSize());
		Page<ProductSearchResult> page = useCase.execute(query);
		List<EntityModel<ProductSearchResultResponse>> models = page.getContent().stream()
				.map(assembler::toSearchResultModel)
				.collect(Collectors.toList());
		PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
				page.getPageSize(), page.getPageNumber(), page.getTotalElements(), page.getTotalPages());
		PagedModel<EntityModel<ProductSearchResultResponse>> pagedModel =
				PagedModel.of(models, metadata,
						linkTo(methodOn(SearchProductsController.class)
								.search(categoryId, description, condition, maxPrice, pageable)).withSelfRel());
		return ResponseEntity.ok(pagedModel);
	}

}

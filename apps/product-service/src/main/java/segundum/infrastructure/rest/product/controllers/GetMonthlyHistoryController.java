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

import segundum.application.queries.GetMonthlyHistoryQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.ProductSummary;
import segundum.application.usecases.GetMonthlyHistoryUseCase;
import segundum.infrastructure.rest.product.api.GetMonthlyHistoryApi;
import segundum.infrastructure.rest.product.assemblers.ProductModelAssembler;
import segundum.infrastructure.rest.product.responses.ProductSummaryResponse;

@RestController
public class GetMonthlyHistoryController implements GetMonthlyHistoryApi {

	private final GetMonthlyHistoryUseCase useCase;
	private final ProductModelAssembler assembler;

	public GetMonthlyHistoryController(GetMonthlyHistoryUseCase useCase,
			ProductModelAssembler assembler) {
		this.useCase = useCase;
		this.assembler = assembler;
	}

	@Override
	public ResponseEntity<PagedModel<EntityModel<ProductSummaryResponse>>> getMonthlyHistory(
			int year, int month, Pageable pageable) {
		GetMonthlyHistoryQuery query = new GetMonthlyHistoryQuery(
				month, year, pageable.getPageNumber(), pageable.getPageSize());
		Page<ProductSummary> page = useCase.execute(query);
		List<EntityModel<ProductSummaryResponse>> models = page.getContent().stream()
				.map(assembler::toSummaryModel)
				.collect(Collectors.toList());
		PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
				page.getPageSize(), page.getPageNumber(), page.getTotalElements(), page.getTotalPages());
		PagedModel<EntityModel<ProductSummaryResponse>> pagedModel =
				PagedModel.of(models, metadata,
						linkTo(methodOn(GetMonthlyHistoryController.class)
								.getMonthlyHistory(year, month, pageable)).withSelfRel());
		return ResponseEntity.ok(pagedModel);
	}

}

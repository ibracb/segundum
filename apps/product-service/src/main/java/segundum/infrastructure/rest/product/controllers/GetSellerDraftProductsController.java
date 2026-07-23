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

import segundum.application.queries.GetSellerDraftProductsQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.product.SellerProduct;
import segundum.application.usecases.GetSellerDraftProductsUseCase;
import segundum.domain.models.seller.SellerId;
import segundum.infrastructure.rest.product.api.GetSellerDraftProductsApi;
import segundum.infrastructure.rest.product.assemblers.ProductModelAssembler;
import segundum.infrastructure.rest.product.responses.SellerProductResponse;

@RestController
public class GetSellerDraftProductsController implements GetSellerDraftProductsApi {

	private final GetSellerDraftProductsUseCase useCase;
	private final ProductModelAssembler assembler;

	public GetSellerDraftProductsController(GetSellerDraftProductsUseCase useCase,
			ProductModelAssembler assembler) {
		this.useCase = useCase;
		this.assembler = assembler;
	}

	@Override
	public ResponseEntity<PagedModel<EntityModel<SellerProductResponse>>> getSellerDraftProducts(
			String id, Pageable pageable) {
		SellerId sellerId = SellerId.fromString(id);
		GetSellerDraftProductsQuery query = new GetSellerDraftProductsQuery(
				sellerId, pageable.getPageNumber(), pageable.getPageSize());
		Page<SellerProduct> page = useCase.execute(query);
		List<EntityModel<SellerProductResponse>> models = page.getContent().stream()
				.map(assembler::toSellerProductModel)
				.collect(Collectors.toList());
		PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
				page.getPageSize(), page.getPageNumber(), page.getTotalElements(), page.getTotalPages());
		PagedModel<EntityModel<SellerProductResponse>> pagedModel =
				PagedModel.of(models, metadata,
						linkTo(methodOn(GetSellerDraftProductsController.class)
								.getSellerDraftProducts(id, pageable)).withSelfRel());
		return ResponseEntity.ok(pagedModel);
	}

}

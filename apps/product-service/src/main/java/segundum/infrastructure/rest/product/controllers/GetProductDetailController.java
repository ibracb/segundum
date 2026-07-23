package segundum.infrastructure.rest.product.controllers;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.queries.GetProductDetailQuery;
import segundum.application.readmodels.product.ProductDetail;
import segundum.application.usecases.GetProductDetailUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.GetProductDetailApi;
import segundum.infrastructure.rest.product.assemblers.ProductModelAssembler;
import segundum.infrastructure.rest.product.responses.ProductDetailResponse;

@RestController
public class GetProductDetailController implements GetProductDetailApi {

	private final GetProductDetailUseCase useCase;
	private final ProductModelAssembler assembler;

	public GetProductDetailController(GetProductDetailUseCase useCase,
			ProductModelAssembler assembler) {
		this.useCase = useCase;
		this.assembler = assembler;
	}

	@Override
	public ResponseEntity<EntityModel<ProductDetailResponse>> getProductDetail(String id) {
		ProductId productId = ProductId.fromString(id);
		ProductDetail product = useCase.execute(new GetProductDetailQuery(productId))
				.orElseThrow(() -> new EntityNotFoundException("Product", id));
		return ResponseEntity.ok(assembler.toDetailModel(product));
	}

}

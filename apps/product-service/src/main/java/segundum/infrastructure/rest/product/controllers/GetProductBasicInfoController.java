package segundum.infrastructure.rest.product.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.queries.GetProductBasicInfoQuery;
import segundum.application.readmodels.product.ProductBasicInfo;
import segundum.application.usecases.GetProductBasicInfoUseCase;
import segundum.domain.exceptions.EntityNotFoundException;
import segundum.domain.models.product.ProductId;
import segundum.infrastructure.rest.product.api.GetProductBasicInfoApi;
import segundum.infrastructure.rest.product.responses.PickupLocationResponse;
import segundum.infrastructure.rest.product.responses.ProductBasicInfoResponse;

@RestController
/**
 * Represents the controller for retrieving the basic information of a product.
 */
public class GetProductBasicInfoController implements GetProductBasicInfoApi {

    private final GetProductBasicInfoUseCase useCase;

    public GetProductBasicInfoController(GetProductBasicInfoUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public ResponseEntity<ProductBasicInfoResponse> getProductBasicInfo(String id) {
        ProductId productId = ProductId.fromString(id);
        ProductBasicInfo product = useCase.execute(new GetProductBasicInfoQuery(productId))
                .orElseThrow(() -> new EntityNotFoundException("Product", id));

        PickupLocationResponse pickup = product.getPickupLocation() != null
                ? new PickupLocationResponse(
                        product.getPickupLocation().getDescription(),
                        product.getPickupLocation().getLatitude(),
                        product.getPickupLocation().getLongitude())
                : null;

        ProductBasicInfoResponse response = new ProductBasicInfoResponse(
                product.getProductId(),
                product.getTitle(),
                product.getPrice(),
                pickup,
                product.getSellerId(),
                product.getSaleStatus());

        return ResponseEntity.ok(response);
    }

}

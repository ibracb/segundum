package segundum.infrastructure.client.product;

import java.io.IOException;

import org.springframework.stereotype.Component;

import retrofit2.Response;
import segundum.application.gateways.GetProductBasicInfo;
import segundum.application.readmodels.product.PickupLocationReadModel;
import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.domain.models.sale.ProductId;
import segundum.infrastructure.client.ExternalServiceException;

/**
 * Represents the Retrofit gateway for fetching basic product information.
 */
@Component
public class ProductBasicInfoRetrofitGateway implements GetProductBasicInfo {

    /**
     * The Retrofit API for the product service.
     */
    private final ProductApi productApi;

    /**
     * Constructs a new ProductBasicInfoRetrofitGateway with the given API.
     *
     * @param productApi the Retrofit API for the product service
     */
    public ProductBasicInfoRetrofitGateway(ProductApi productApi) {
        this.productApi = productApi;
    }

    @Override
    public ProductBasicInfoReadModel fetch(ProductId productId) {
        Response<ProductBasicInfoResponse> response;
        try {
            response = productApi.getProductBasicInfo(productId.getValue().toString()).execute();
        } catch (IOException e) {
            throw new ExternalServiceException("Could not fetch product " + productId.getValue(), e);
        }
        if (!response.isSuccessful() || response.body() == null) {
            throw new ExternalServiceException("Product not found: " + productId.getValue());
        }
        return toReadModel(response.body());
    }

    /**
     * Converts the given response DTO into a read model.
     *
     * @param dto the response DTO
     * @return the product basic information read model
     */
    private ProductBasicInfoReadModel toReadModel(ProductBasicInfoResponse dto) {
        PickupLocationReadModel pickupLocation = dto.getPickupLocation() != null
                ? new PickupLocationReadModel(
                        dto.getPickupLocation().getDescription(),
                        dto.getPickupLocation().getLatitude(),
                        dto.getPickupLocation().getLongitude())
                : null;
        return new ProductBasicInfoReadModel(
                dto.getProductId(),
                dto.getTitle(),
                dto.getPrice(),
                pickupLocation,
                dto.getSellerId(),
                dto.getSaleStatus());
    }

}

package segundum.infrastructure.rest.sale.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the request to cancel a sale by the seller.
 */
@Schema(description = "Request to cancel a sale by the seller")
public class CancelSaleBySellerRequest {

    /**
     * The identifier of the seller.
     */
    @NotNull
    @NotBlank
    @Schema(description = "Seller identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String sellerId;

    /**
     * Constructs a new empty CancelSaleBySellerRequest for deserialization.
     */
    public CancelSaleBySellerRequest() {
    }

    /**
     * Returns the identifier of the seller.
     *
     * @return the identifier of the seller
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * Sets the identifier of the seller.
     *
     * @param sellerId the identifier of the seller
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

}

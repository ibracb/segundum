package segundum.infrastructure.rest.sale.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the request to cancel a sale by the purchaser.
 */
@Schema(description = "Request to cancel a sale by the purchaser")
public class CancelSaleByPurchaserRequest {

    /**
     * The identifier of the purchaser.
     */
    @NotNull
    @NotBlank
    @Schema(description = "Purchaser identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String purchaserId;

    /**
     * Constructs a new empty CancelSaleByPurchaserRequest for deserialization.
     */
    public CancelSaleByPurchaserRequest() {
    }

    /**
     * Returns the identifier of the purchaser.
     *
     * @return the identifier of the purchaser
     */
    public String getPurchaserId() {
        return purchaserId;
    }

    /**
     * Sets the identifier of the purchaser.
     *
     * @param purchaserId the identifier of the purchaser
     */
    public void setPurchaserId(String purchaserId) {
        this.purchaserId = purchaserId;
    }

}

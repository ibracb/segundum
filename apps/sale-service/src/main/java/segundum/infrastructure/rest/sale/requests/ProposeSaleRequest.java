package segundum.infrastructure.rest.sale.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the request to propose a new sale.
 */
@Schema(description = "Request to propose a new sale")
public class ProposeSaleRequest {

    /**
     * The identifier of the product.
     */
    @NotNull
    @NotBlank
    @Schema(description = "Product identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String productId;

    /**
     * The identifier of the purchaser.
     */
    @NotNull
    @NotBlank
    @Schema(description = "Purchaser identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String purchaserId;

    /**
     * Constructs a new empty ProposeSaleRequest for deserialization.
     */
    public ProposeSaleRequest() {
    }

    /**
     * Returns the identifier of the product.
     *
     * @return the identifier of the product
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the identifier of the product.
     *
     * @param productId the identifier of the product
     */
    public void setProductId(String productId) {
        this.productId = productId;
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

package segundum.infrastructure.rest.sale.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the parties of a sale, along with its current status.
 */
@Schema(description = "Parties of a sale (purchaser and seller) and its current status")
public class SalePartiesResponse {

    @Schema(description = "Purchaser identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String purchaserId;

    @Schema(description = "Seller identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String sellerId;

    @Schema(description = "Current status of the sale", example = "COMPLETED")
    private String status;

    /**
     * Constructs a new empty SalePartiesResponse for deserialization.
     */
    public SalePartiesResponse() {
    }

    /**
     * Constructs a new SalePartiesResponse with the given values.
     *
     * @param purchaserId the purchaser identifier
     * @param sellerId    the seller identifier
     * @param status      the current status of the sale
     */
    public SalePartiesResponse(String purchaserId, String sellerId, String status) {
        this.purchaserId = purchaserId;
        this.sellerId = sellerId;
        this.status = status;
    }

    public String getPurchaserId() { return purchaserId; }
    public String getSellerId() { return sellerId; }
    public String getStatus() { return status; }

    public void setPurchaserId(String purchaserId) { this.purchaserId = purchaserId; }
    public void setSellerId(String sellerId) { this.sellerId = sellerId; }
    public void setStatus(String status) { this.status = status; }

}

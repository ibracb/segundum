package segundum.infrastructure.rest.sale.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a sale as seen by the seller.
 */
@Schema(description = "Sale as seen by the seller")
public class SaleAsSellerResponse {

    /**
     * The sale identifier.
     */
    @Schema(description = "Sale identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String saleId;

    /**
     * The sale status.
     */
    @Schema(description = "Sale status", example = "PENDING")
    private String status;

    /**
     * The sale datetime.
     */
    @Schema(description = "Sale datetime", example = "2026-08-07T10:15:30")
    private String datetime;

    /**
     * The product information.
     */
    @Schema(description = "Product information")
    private ProductBasicInfoResponse product;

    /**
     * The purchaser information.
     */
    @Schema(description = "Purchaser information")
    private PurchaserResponse purchaser;

    /**
     * Constructs a new empty SaleAsSellerResponse for deserialization.
     */
    public SaleAsSellerResponse() {
    }

    /**
     * Constructs a new SaleAsSellerResponse with the given values.
     *
     * @param saleId    the sale identifier
     * @param status    the sale status
     * @param datetime  the sale datetime
     * @param product   the product information
     * @param purchaser the purchaser information
     */
    public SaleAsSellerResponse(String saleId, String status, String datetime,
            ProductBasicInfoResponse product, PurchaserResponse purchaser) {
        this.saleId = saleId;
        this.status = status;
        this.datetime = datetime;
        this.product = product;
        this.purchaser = purchaser;
    }

    /**
     * Returns the sale identifier.
     *
     * @return the sale identifier
     */
    public String getSaleId() {
        return saleId;
    }

    /**
     * Returns the sale status.
     *
     * @return the sale status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the sale datetime.
     *
     * @return the sale datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Returns the product information.
     *
     * @return the product information
     */
    public ProductBasicInfoResponse getProduct() {
        return product;
    }

    /**
     * Returns the purchaser information.
     *
     * @return the purchaser information
     */
    public PurchaserResponse getPurchaser() {
        return purchaser;
    }

    /**
     * Sets the sale identifier.
     *
     * @param saleId the sale identifier
     */
    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    /**
     * Sets the sale status.
     *
     * @param status the sale status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the sale datetime.
     *
     * @param datetime the sale datetime
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * Sets the product information.
     *
     * @param product the product information
     */
    public void setProduct(ProductBasicInfoResponse product) {
        this.product = product;
    }

    /**
     * Sets the purchaser information.
     *
     * @param purchaser the purchaser information
     */
    public void setPurchaser(PurchaserResponse purchaser) {
        this.purchaser = purchaser;
    }

}

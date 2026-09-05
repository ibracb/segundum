package segundum.infrastructure.rest.sale.responses;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the full detail of a sale.
 */
@Schema(description = "Full sale detail")
public class SaleDetailResponse {

    @Schema(description = "Sale identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String saleId;

    @Schema(description = "Sale status", example = "PENDING")
    private String status;

    @Schema(description = "Sale datetime", example = "2026-08-07T10:15:30")
    private String datetime;

    @Schema(description = "Product information")
    private ProductBasicInfoResponse product;

    @Schema(description = "Seller information")
    private SellerResponse seller;

    @Schema(description = "Purchaser information")
    private PurchaserResponse purchaser;

    /**
     * Constructs a new empty SaleDetailResponse for deserialization.
     */
    public SaleDetailResponse() {
    }

    /**
     * Constructs a new SaleDetailResponse with the given values.
     *
     * @param saleId    the sale identifier
     * @param status    the sale status
     * @param datetime  the sale datetime
     * @param product   the product information
     * @param seller    the seller information
     * @param purchaser the purchaser information
     */
    public SaleDetailResponse(String saleId, String status, String datetime,
            ProductBasicInfoResponse product, SellerResponse seller, PurchaserResponse purchaser) {
        this.saleId = saleId;
        this.status = status;
        this.datetime = datetime;
        this.product = product;
        this.seller = seller;
        this.purchaser = purchaser;
    }

    public String getSaleId() { return saleId; }
    public String getStatus() { return status; }
    public String getDatetime() { return datetime; }
    public ProductBasicInfoResponse getProduct() { return product; }
    public SellerResponse getSeller() { return seller; }
    public PurchaserResponse getPurchaser() { return purchaser; }

    public void setSaleId(String saleId) { this.saleId = saleId; }
    public void setStatus(String status) { this.status = status; }
    public void setDatetime(String datetime) { this.datetime = datetime; }
    public void setProduct(ProductBasicInfoResponse product) { this.product = product; }
    public void setSeller(SellerResponse seller) { this.seller = seller; }
    public void setPurchaser(PurchaserResponse purchaser) { this.purchaser = purchaser; }

}

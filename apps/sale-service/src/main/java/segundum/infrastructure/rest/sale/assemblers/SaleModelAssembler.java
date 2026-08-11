package segundum.infrastructure.rest.sale.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import segundum.application.readmodels.product.ProductBasicInfoReadModel;
import segundum.application.readmodels.purchaser.PurchaserReadModel;
import segundum.application.readmodels.sale.SaleAsPurchaserReadModel;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.infrastructure.rest.sale.responses.PickupLocationResponse;
import segundum.infrastructure.rest.sale.responses.ProductBasicInfoResponse;
import segundum.infrastructure.rest.sale.responses.PurchaserResponse;
import segundum.infrastructure.rest.sale.responses.SaleAsPurchaserResponse;
import segundum.infrastructure.rest.sale.responses.SaleAsSellerResponse;
import segundum.infrastructure.rest.sale.responses.SellerResponse;

/**
 * Represents the assembler that converts sale read models into HATEOAS entity models.
 */
@Component
public class SaleModelAssembler {

    /**
     * Converts a sale read model into an entity model as seen by the purchaser.
     *
     * @param sale the sale read model
     * @return the entity model as seen by the purchaser
     */
    public EntityModel<SaleAsPurchaserResponse> toSaleAsPurchaserModel(SaleAsPurchaserReadModel sale) {
        SaleAsPurchaserResponse response = new SaleAsPurchaserResponse(
                sale.getSaleId(),
                sale.getStatus(),
                sale.getDatetime(),
                toProductResponse(sale.getProduct()),
                toSellerResponse(sale.getSeller()));
        return EntityModel.of(response);
    }

    /**
     * Converts a sale read model into an entity model as seen by the seller.
     *
     * @param sale the sale read model
     * @return the entity model as seen by the seller
     */
    public EntityModel<SaleAsSellerResponse> toSaleAsSellerModel(SaleAsSellerReadModel sale) {
        SaleAsSellerResponse response = new SaleAsSellerResponse(
                sale.getSaleId(),
                sale.getStatus(),
                sale.getDatetime(),
                toProductResponse(sale.getProduct()),
                toPurchaserResponse(sale.getPurchaser()));
        return EntityModel.of(response);
    }

    /**
     * Converts a product read model into a response.
     *
     * @param product the product read model
     * @return the product basic information response
     */
    private ProductBasicInfoResponse toProductResponse(ProductBasicInfoReadModel product) {
        PickupLocationResponse pickup = product.getPickupLocation() != null
                ? new PickupLocationResponse(
                        product.getPickupLocation().getDescription(),
                        product.getPickupLocation().getLatitude(),
                        product.getPickupLocation().getLongitude())
                : null;
        return new ProductBasicInfoResponse(
                product.getProductId(),
                product.getTitle(),
                product.getPrice(),
                pickup,
                product.getSellerId());
    }

    /**
     * Converts a seller read model into a response.
     *
     * @param seller the seller read model
     * @return the seller response
     */
    private SellerResponse toSellerResponse(SellerReadModel seller) {
        return new SellerResponse(seller.getId(), seller.getName(), seller.getSurname());
    }

    /**
     * Converts a purchaser read model into a response.
     *
     * @param purchaser the purchaser read model
     * @return the purchaser response
     */
    private PurchaserResponse toPurchaserResponse(PurchaserReadModel purchaser) {
        return new PurchaserResponse(purchaser.getId(), purchaser.getName(), purchaser.getSurname());
    }

}

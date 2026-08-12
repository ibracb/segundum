package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.CancelSaleByPurchaserCommand;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SaleId;
import segundum.infrastructure.facades.CancelSaleByPurchaserFacade;
import segundum.infrastructure.rest.sale.api.CancelSaleByPurchaserApi;
import segundum.infrastructure.rest.sale.requests.CancelSaleByPurchaserRequest;

/**
 * Represents the controller for cancelling a sale by the purchaser.
 */
@RestController
public class CancelSaleByPurchaserController implements CancelSaleByPurchaserApi {

    /**
     * The facade for cancelling a sale by the purchaser.
     */
    private final CancelSaleByPurchaserFacade facade;

    /**
     * Constructs a new CancelSaleByPurchaserController with the given facade.
     *
     * @param facade the facade for cancelling a sale by the purchaser
     */
    public CancelSaleByPurchaserController(CancelSaleByPurchaserFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<Void> cancelSaleByPurchaser(String id, CancelSaleByPurchaserRequest request) {
        facade.run(new CancelSaleByPurchaserCommand(
                SaleId.fromString(id),
                PurchaserId.fromString(request.getPurchaserId())));
        return ResponseEntity.noContent().build();
    }

}

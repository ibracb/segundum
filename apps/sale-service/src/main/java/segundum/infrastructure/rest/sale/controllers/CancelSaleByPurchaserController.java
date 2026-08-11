package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.CancelSaleByPurchaserCommand;
import segundum.application.usecases.CancelSaleByPurchaserUseCase;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.SaleId;
import segundum.infrastructure.rest.sale.api.CancelSaleByPurchaserApi;
import segundum.infrastructure.rest.sale.requests.CancelSaleByPurchaserRequest;

/**
 * Represents the controller for cancelling a sale by the purchaser.
 */
@RestController
public class CancelSaleByPurchaserController implements CancelSaleByPurchaserApi {

    /**
     * The use case for cancelling a sale by the purchaser.
     */
    private final CancelSaleByPurchaserUseCase cancelSaleByPurchaserUseCase;

    /**
     * Constructs a new CancelSaleByPurchaserController with the given use case.
     *
     * @param cancelSaleByPurchaserUseCase the use case for cancelling a sale by the purchaser
     */
    public CancelSaleByPurchaserController(CancelSaleByPurchaserUseCase cancelSaleByPurchaserUseCase) {
        this.cancelSaleByPurchaserUseCase = cancelSaleByPurchaserUseCase;
    }

    @Override
    public ResponseEntity<Void> cancelSaleByPurchaser(String id, CancelSaleByPurchaserRequest request) {
        cancelSaleByPurchaserUseCase.execute(new CancelSaleByPurchaserCommand(
                SaleId.fromString(id),
                PurchaserId.fromString(request.getPurchaserId())));
        return ResponseEntity.noContent().build();
    }

}

package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.CancelSaleBySellerCommand;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.facades.CancelSaleBySellerFacade;
import segundum.infrastructure.rest.sale.api.CancelSaleBySellerApi;
import segundum.infrastructure.rest.sale.requests.CancelSaleBySellerRequest;

/**
 * Represents the controller for cancelling a sale by the seller.
 */
@RestController
public class CancelSaleBySellerController implements CancelSaleBySellerApi {

    /**
     * The facade for cancelling a sale by the seller.
     */
    private final CancelSaleBySellerFacade facade;

    /**
     * Constructs a new CancelSaleBySellerController with the given facade.
     *
     * @param facade the facade for cancelling a sale by the seller
     */
    public CancelSaleBySellerController(CancelSaleBySellerFacade facade) {
        this.facade = facade;
    }

    @Override
    public ResponseEntity<Void> cancelSaleBySeller(String id, CancelSaleBySellerRequest request) {
        facade.run(new CancelSaleBySellerCommand(
                SaleId.fromString(id),
                SellerId.fromString(request.getSellerId())));
        return ResponseEntity.noContent().build();
    }

}

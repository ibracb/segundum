package segundum.infrastructure.rest.sale.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.commands.CancelSaleBySellerCommand;
import segundum.application.usecases.CancelSaleBySellerUseCase;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.rest.sale.api.CancelSaleBySellerApi;
import segundum.infrastructure.rest.sale.requests.CancelSaleBySellerRequest;

/**
 * Represents the controller for cancelling a sale by the seller.
 */
@RestController
public class CancelSaleBySellerController implements CancelSaleBySellerApi {

    /**
     * The use case for cancelling a sale by the seller.
     */
    private final CancelSaleBySellerUseCase cancelSaleBySellerUseCase;

    /**
     * Constructs a new CancelSaleBySellerController with the given use case.
     *
     * @param cancelSaleBySellerUseCase the use case for cancelling a sale by the seller
     */
    public CancelSaleBySellerController(CancelSaleBySellerUseCase cancelSaleBySellerUseCase) {
        this.cancelSaleBySellerUseCase = cancelSaleBySellerUseCase;
    }

    @Override
    public ResponseEntity<Void> cancelSaleBySeller(String id, CancelSaleBySellerRequest request) {
        cancelSaleBySellerUseCase.execute(new CancelSaleBySellerCommand(
                SaleId.fromString(id),
                SellerId.fromString(request.getSellerId())));
        return ResponseEntity.noContent().build();
    }

}

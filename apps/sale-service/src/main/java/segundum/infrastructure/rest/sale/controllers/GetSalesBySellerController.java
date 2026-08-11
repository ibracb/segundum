package segundum.infrastructure.rest.sale.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import segundum.application.queries.GetSalesBySellerQuery;
import segundum.application.readmodels.common.Page;
import segundum.application.readmodels.sale.SaleAsSellerReadModel;
import segundum.application.usecases.GetSalesBySellerUseCase;
import segundum.domain.models.sale.OrderStatus;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.rest.sale.api.GetSalesBySellerApi;
import segundum.infrastructure.rest.sale.assemblers.SaleModelAssembler;
import segundum.infrastructure.rest.sale.responses.SaleAsSellerResponse;

/**
 * Represents the controller for fetching the sales of a seller.
 */
@RestController
public class GetSalesBySellerController implements GetSalesBySellerApi {

    /**
     * The use case for fetching the sales of a seller.
     */
    private final GetSalesBySellerUseCase useCase;

    /**
     * The assembler for converting sale read models into entity models.
     */
    private final SaleModelAssembler assembler;

    /**
     * Constructs a new GetSalesBySellerController with the given dependencies.
     *
     * @param useCase   the use case for fetching the sales of a seller
     * @param assembler the assembler for converting sale read models into entity models
     */
    public GetSalesBySellerController(GetSalesBySellerUseCase useCase, SaleModelAssembler assembler) {
        this.useCase = useCase;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<SaleAsSellerResponse>>> getSalesBySeller(
            String sellerId, String status, Pageable pageable) {
        GetSalesBySellerQuery query = new GetSalesBySellerQuery(
                SellerId.fromString(sellerId),
                parseStatus(status),
                pageable.getPageNumber(), pageable.getPageSize());
        Page<SaleAsSellerReadModel> page = useCase.execute(query);
        List<EntityModel<SaleAsSellerResponse>> models = page.getContent().stream()
                .map(assembler::toSaleAsSellerModel)
                .collect(Collectors.toList());
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
                page.getPageSize(), page.getPageNumber(), page.getTotalElements(), page.getTotalPages());
        return ResponseEntity.ok(PagedModel.of(models, metadata));
    }

    /**
     * Parses the given status string into an order status.
     *
     * @param status the status string
     * @return the order status, or null if the status is blank
     * @throws IllegalArgumentException if the status is not a valid order status
     */
    private OrderStatus parseStatus(String status) {
        if (status == null || status.isBlank()) {
            return null;
        }
        String value = status.trim();
        for (OrderStatus candidate : OrderStatus.values()) {
            if (candidate.name().equals(value)) {
                return candidate;
            }
        }
        throw new IllegalArgumentException("Invalid sale status '" + value
                + "'. Valid values: PENDING, RESERVED, REJECTED, CANCELLED, COMPLETED");
    }

}

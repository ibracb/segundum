package segundum.infrastructure.client.user;

import java.io.IOException;

import org.springframework.stereotype.Component;

import retrofit2.Response;
import segundum.application.gateways.GetPurchaser;
import segundum.application.readmodels.purchaser.PurchaserReadModel;
import segundum.domain.models.sale.PurchaserId;
import segundum.infrastructure.client.ExternalServiceException;

/**
 * Represents the Retrofit gateway for fetching a purchaser.
 */
@Component
public class PurchaserRetrofitGateway implements GetPurchaser {

    /**
     * The Retrofit API for the user service.
     */
    private final UserApi userApi;

    /**
     * Constructs a new PurchaserRetrofitGateway with the given API.
     *
     * @param userApi the Retrofit API for the user service
     */
    public PurchaserRetrofitGateway(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public PurchaserReadModel fetch(PurchaserId purchaserId) {
        Response<UserNameResponse> response;
        try {
            response = userApi.getUserName(purchaserId.getValue().toString()).execute();
        } catch (IOException e) {
            throw new ExternalServiceException("Could not fetch purchaser " + purchaserId.getValue(), e);
        }
        if (!response.isSuccessful() || response.body() == null) {
            throw new ExternalServiceException("Purchaser not found: " + purchaserId.getValue());
        }
        UserNameResponse dto = response.body();
        return new PurchaserReadModel(dto.getId(), dto.getName(), dto.getSurname());
    }

}

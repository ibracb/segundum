package segundum.infrastructure.client.user;

import java.io.IOException;

import org.springframework.stereotype.Component;

import retrofit2.Response;
import segundum.application.gateways.GetSeller;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.domain.models.sale.SellerId;
import segundum.infrastructure.client.ExternalServiceException;

/**
 * Represents the Retrofit gateway for fetching a seller.
 */
@Component
public class SellerRetrofitGateway implements GetSeller {

    /**
     * The Retrofit API for the user service.
     */
    private final UserApi userApi;

    /**
     * Constructs a new SellerRetrofitGateway with the given API.
     *
     * @param userApi the Retrofit API for the user service
     */
    public SellerRetrofitGateway(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public SellerReadModel fetch(SellerId sellerId) {
        Response<UserNameResponse> response;
        try {
            response = userApi.getUserName(sellerId.getValue().toString()).execute();
        } catch (IOException e) {
            throw new ExternalServiceException("Could not fetch seller " + sellerId.getValue(), e);
        }
        if (!response.isSuccessful() || response.body() == null) {
            throw new ExternalServiceException("Seller not found: " + sellerId.getValue());
        }
        UserNameResponse dto = response.body();
        return new SellerReadModel(dto.getId(), dto.getName(), dto.getSurname());
    }

}

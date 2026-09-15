package segundum.infrastructure.client.user;

import java.io.IOException;

import org.springframework.stereotype.Component;

import retrofit2.Response;
import segundum.application.gateways.AuthenticateUser;
import segundum.application.readmodels.auth.AuthenticatedUserReadModel;
import segundum.infrastructure.client.ExternalServiceException;

/**
 * Retrofit gateway for authenticating users against the user service.
 */
@Component
public class AuthenticateUserRetrofitGateway implements AuthenticateUser {

    /**
     * The Retrofit API for the user service.
     */
    private final UserApi userApi;

    /**
     * Constructs a new AuthenticateUserRetrofitGateway with the given API.
     *
     * @param userApi the Retrofit API for the user service
     */
    public AuthenticateUserRetrofitGateway(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public AuthenticatedUserReadModel fetch(String email, String password) {
        Response<AuthenticatedUserDTO> response;
        try {
            response = userApi.authenticate(new AuthenticateUserRequest(email, password)).execute();
        } catch (IOException e) {
            throw new ExternalServiceException("Could not authenticate user", e);
        }
        if (!response.isSuccessful() || response.body() == null) {
            return null;
        }
        AuthenticatedUserDTO dto = response.body();
        return new AuthenticatedUserReadModel(dto.getId(), dto.getFullName(), dto.getRoles());
    }

}

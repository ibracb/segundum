package segundum.infrastructure.client.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit API for the user service authentication endpoint.
 */
public interface UserApi {

    /**
     * Authenticates a user with the given credentials.
     *
     * @param request the authentication request containing email and password
     * @return a call that yields the authenticated user data
     */
    @POST("authenticate")
    Call<AuthenticatedUserDTO> authenticate(@Body AuthenticateUserRequest request);

}

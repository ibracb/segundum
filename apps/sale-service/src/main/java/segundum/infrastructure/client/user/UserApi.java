package segundum.infrastructure.client.user;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Represents the Retrofit API for the user service.
 */
public interface UserApi {

    /**
     * Fetches the name of a user.
     *
     * @param id the identifier of the user
     * @return a call that yields the name of the user
     */
    @GET("api/users/{id}/name")
    Call<UserNameResponse> getUserName(@Path("id") String id);

}

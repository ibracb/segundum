package segundum.infrastructure.client.product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Represents the Retrofit API for the product service.
 */
public interface ProductApi {

    /**
     * Fetches the basic information of a product.
     *
     * @param id the identifier of the product
     * @return a call that yields the basic information of the product
     */
    @GET("api/products/{id}/basic-info")
    Call<ProductBasicInfoResponse> getProductBasicInfo(@Path("id") String id);

}

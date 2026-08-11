package segundum.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import segundum.infrastructure.client.product.ProductApi;
import segundum.infrastructure.client.user.UserApi;

/**
 * Represents the Retrofit configuration for the sale service.
 */
@Configuration
public class RetrofitConfig {
	
	/**
	 * The URL of the user service.
	 */
	@Value("${user.service.url}")
    private String userServiceUrl;
	
	/**
	 * The URL of the product service.
	 */
    @Value("${product.service.url}")
    private String productServiceUrl;

    /**
     * Creates the user service Retrofit bean.
     *
     * @return the user service Retrofit instance
     */
    @Bean
    public Retrofit userRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(withTrailingSlash(userServiceUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    
    /**
     * Creates the product service Retrofit bean.
     *
     * @return the product service Retrofit instance
     */
    @Bean
    public Retrofit productRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(withTrailingSlash(productServiceUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    
    /**
     * Creates the user API bean.
     *
     * @param userRetrofit the user service Retrofit instance
     * @return the user API
     */
    @Bean
    public UserApi userApi(Retrofit userRetrofit) {
        return userRetrofit.create(UserApi.class);
    }
    
    /**
     * Creates the product API bean.
     *
     * @param productRetrofit the product service Retrofit instance
     * @return the product API
     */
    @Bean
    public ProductApi productApi(Retrofit productRetrofit) {
        return productRetrofit.create(ProductApi.class);
    }

    /**
     * Appends a trailing slash to the given URL if it does not already end with one.
     *
     * @param url the URL
     * @return the URL with a trailing slash
     */
    private static String withTrailingSlash(String url) {
        return url.endsWith("/") ? url : url + "/";
    }

}

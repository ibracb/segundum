package segundum.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import segundum.infrastructure.client.user.UserApi;

/**
 * Retrofit configuration for calling the user service.
 */
@Configuration
public class RetrofitConfig {

    /**
     * The URL of the user service.
     */
    @Value("${user.route}")
    private String userRoute;

    /**
     * Creates the user service Retrofit bean.
     *
     * @return the Retrofit instance for the user service
     */
    @Bean
    public Retrofit userRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(withTrailingSlash(userRoute))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Creates the user API bean.
     *
     * @param userRetrofit the Retrofit instance for the user service
     * @return the user API
     */
    @Bean
    public UserApi userApi(Retrofit userRetrofit) {
        return userRetrofit.create(UserApi.class);
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

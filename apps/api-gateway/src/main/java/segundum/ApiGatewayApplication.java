package segundum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Main application class for the API Gateway.
 */
@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayApplication {

    /**
     * Starts the API Gateway application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}

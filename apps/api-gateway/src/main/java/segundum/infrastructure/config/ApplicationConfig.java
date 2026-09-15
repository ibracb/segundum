package segundum.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import segundum.application.gateways.AuthenticateUser;
import segundum.application.usecases.LoginUseCase;
import segundum.application.usecases.interactors.LoginInteractor;

/**
 * Spring configuration for application handler beans.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public LoginUseCase loginUseCase(AuthenticateUser authenticateUser) {
        return new LoginInteractor(authenticateUser);
    }
}

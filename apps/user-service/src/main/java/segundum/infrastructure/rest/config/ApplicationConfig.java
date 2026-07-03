package segundum.infrastructure.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import segundum.application.interactors.DeleteUserInteractor;
import segundum.application.interactors.GetUserProfileInteractor;
import segundum.application.interactors.RegisterUserInteractor;
import segundum.application.interactors.UpdateUserProfileInteractor;
import segundum.application.usecases.DeleteUserUseCase;
import segundum.application.usecases.GetUserProfileUseCase;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.application.usecases.UpdateUserProfileUseCase;
import segundum.domain.events.DomainEventPublisher;
import segundum.domain.repositories.UserRepository;
import segundum.infrastructure.gson.GsonConfig;
import segundum.infrastructure.messaging.rabbitmq.RabbitMQEventPublisher;
import segundum.infrastructure.persistence.jpa.repositories.JpaUserRepository;
import segundum.infrastructure.rest.controllers.UserController;
import segundum.infrastructure.rest.handlers.DomainExceptionMapper;
import segundum.infrastructure.rest.handlers.EmailAlreadyExistsExceptionMapper;
import segundum.infrastructure.rest.handlers.EntityNotFoundExceptionMapper;
import segundum.infrastructure.rest.handlers.GenericExceptionMapper;
import segundum.infrastructure.rest.handlers.PhoneAlreadyExistsExceptionMapper;
import segundum.infrastructure.rest.handlers.SameValueExceptionMapper;

/**
 * Application configuration for the user service REST API.
 * <p>
 * Serves as the Composition Root where all dependencies are instantiated and wired.
 * Replaces the previous Service Locator pattern (factories + .properties) with
 * explicit constructor injection.
 * </p>
 * <p>
 * Also replaces the {@code web.xml} servlet configuration using {@link ApplicationPath}
 * to define the base URI mapping.
 * </p>
 */
@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

	/**
	 * Initializes the application configuration by registering controllers, use cases, and exception mappers.
	 */
    public ApplicationConfig() {
        UserRepository userRepository = new JpaUserRepository();
        DomainEventPublisher eventPublisher = new RabbitMQEventPublisher();

        RegisterUserUseCase registerUser = new RegisterUserInteractor(userRepository, eventPublisher);
        UpdateUserProfileUseCase updateUser = new UpdateUserProfileInteractor(userRepository, eventPublisher);
        DeleteUserUseCase deleteUser = new DeleteUserInteractor(userRepository, eventPublisher);
        GetUserProfileUseCase getUserProfile = new GetUserProfileInteractor(userRepository);

        register(new UserController(registerUser, updateUser, deleteUser, getUserProfile));
        register(DomainExceptionMapper.class);
        register(EntityNotFoundExceptionMapper.class);
        register(EmailAlreadyExistsExceptionMapper.class);
        register(PhoneAlreadyExistsExceptionMapper.class);
        register(SameValueExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(GsonConfig.class);
    }
}

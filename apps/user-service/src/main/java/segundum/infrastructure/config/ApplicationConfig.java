package segundum.infrastructure.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import segundum.application.usecases.DeactivateUserUseCase;
import segundum.application.usecases.GetUserNameUseCase;
import segundum.application.usecases.GetUserProfileUseCase;
import segundum.application.usecases.GetUserStatsUseCase;
import segundum.application.usecases.RegisterUserUseCase;
import segundum.application.eventhandlers.SaleEventHandler;
import segundum.application.eventhandlers.interactors.SaleEventHandlerInteractor;
import segundum.application.usecases.UpdateUserProfileUseCase;
import segundum.application.usecases.interactors.DeactivateUserInteractor;
import segundum.application.usecases.interactors.GetUserNameInteractor;
import segundum.application.usecases.interactors.GetUserProfileInteractor;
import segundum.application.usecases.interactors.GetUserStatsInteractor;
import segundum.application.usecases.interactors.RegisterUserInteractor;
import segundum.application.usecases.interactors.UpdateUserProfileInteractor;
import segundum.domain.outbound.DomainEventPublisher;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.outbound.PasswordHasher;
import segundum.domain.repositories.UserRepository;
import segundum.infrastructure.gson.GsonConfig;
import segundum.infrastructure.messaging.rabbitmq.RabbitMQEventPublisher;
import segundum.infrastructure.messaging.messages.UserMessageMapper;
import segundum.infrastructure.logging.Slf4jLogEmitter;
import segundum.infrastructure.messaging.rabbitmq.RabbitMQEventConsumer;
import segundum.infrastructure.persistence.jpa.user.JpaUserRepository;
import segundum.infrastructure.rest.config.OpenApiConfig;
import segundum.infrastructure.rest.handlers.EntityNotFoundExceptionMapper;
import segundum.infrastructure.rest.handlers.GenericExceptionMapper;
import segundum.infrastructure.rest.handlers.JsonSyntaxExceptionMapper;
import segundum.infrastructure.rest.user.controllers.DeactivateUserController;
import segundum.infrastructure.rest.user.controllers.GetUserNameController;
import segundum.infrastructure.rest.user.controllers.GetUserProfileController;
import segundum.infrastructure.rest.user.controllers.GetUserStatsController;
import segundum.infrastructure.rest.user.controllers.RegisterUserController;
import segundum.infrastructure.rest.user.controllers.UpdateUserProfileController;
import segundum.infrastructure.rest.user.errorhandlers.DomainExceptionMapper;
import segundum.infrastructure.rest.user.errorhandlers.EmailAlreadyExistsExceptionMapper;
import segundum.infrastructure.rest.user.errorhandlers.PhoneAlreadyExistsExceptionMapper;
import segundum.infrastructure.rest.user.errorhandlers.SameValueExceptionMapper;
import segundum.infrastructure.rest.user.errorhandlers.UserNotActiveExceptionMapper;
import segundum.infrastructure.security.BCryptPasswordHasher;

/**
 * Application configuration for the user service.
 * <p>
 * Serves as the Composition Root where all dependencies are instantiated and wired with
 * explicit constructor injection.
 * </p>
 */
@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

	/**
	 * Initializes the application configuration by registering controllers, use cases, and exception mappers.
	 */
    public ApplicationConfig() {
        PasswordHasher passwordHasher = new BCryptPasswordHasher();
        UserRepository userRepository = new JpaUserRepository();
        DomainEventPublisher eventPublisher = new RabbitMQEventPublisher(new UserMessageMapper());

        RegisterUserUseCase registerUser = new RegisterUserInteractor(userRepository, eventPublisher, passwordHasher);
        UpdateUserProfileUseCase updateUser = new UpdateUserProfileInteractor(userRepository, eventPublisher, passwordHasher);
        DeactivateUserUseCase deactivateUser = new DeactivateUserInteractor(userRepository, eventPublisher);
        GetUserProfileUseCase getUserProfile = new GetUserProfileInteractor(userRepository);
        GetUserStatsUseCase getUserStats = new GetUserStatsInteractor(userRepository);
        GetUserNameUseCase getUserName = new GetUserNameInteractor(userRepository);

        LogEmitter logEmitter = new Slf4jLogEmitter(SaleEventHandlerInteractor.class);
        SaleEventHandler saleEventHandler = new SaleEventHandlerInteractor(userRepository, logEmitter);

        RabbitMQEventConsumer consumer = new RabbitMQEventConsumer(saleEventHandler);
        consumer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(consumer::stop));

        register(new GetUserProfileController(getUserProfile));
        register(new GetUserStatsController(getUserStats));
        register(new GetUserNameController(getUserName));
        register(new RegisterUserController(registerUser));
        register(new UpdateUserProfileController(updateUser));
        register(new DeactivateUserController(deactivateUser));

        register(saleEventHandler);

        register(DomainExceptionMapper.class);
        register(EmailAlreadyExistsExceptionMapper.class);
        register(PhoneAlreadyExistsExceptionMapper.class);
        register(SameValueExceptionMapper.class);
        register(EntityNotFoundExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(JsonSyntaxExceptionMapper.class);
        register(GsonConfig.class);
        register(OpenApiConfig.class);
        register(UserNotActiveExceptionMapper.class);
    }
}

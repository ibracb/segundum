package segundum.infrastructure.rest.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.annotation.security.PermitAll;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.jaxrs2.Reader;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import segundum.infrastructure.rest.user.controllers.AuthenticateUserController;
import segundum.infrastructure.rest.user.controllers.DeactivateUserController;
import segundum.infrastructure.rest.user.controllers.GetUserListController;
import segundum.infrastructure.rest.user.controllers.GetUserNameController;
import segundum.infrastructure.rest.user.controllers.GetUserProfileController;
import segundum.infrastructure.rest.user.controllers.GetUserRatingsController;
import segundum.infrastructure.rest.user.controllers.GetUserStatsController;
import segundum.infrastructure.rest.user.controllers.RegisterUserController;
import segundum.infrastructure.rest.user.controllers.UpdateUserProfileController;
import segundum.infrastructure.utils.PropertiesReader;

@Path("/")
@OpenAPIDefinition(info = @Info(
    title = "SegundUM - User Service API",
    description = "REST API for managing users in the marketplace",
    version = "1.0.0"))
/**
 * Represents the OpenAPI configuration and endpoints for the user service API.
 */
public class OpenApiConfig {

    /**
     * The resource classes exposed by the API.
     */
    private static final Set<Class<?>> RESOURCE_CLASSES = Set.of(
            AuthenticateUserController.class,
            RegisterUserController.class,
            GetUserProfileController.class,
            GetUserStatsController.class,
            UpdateUserProfileController.class,
            DeactivateUserController.class,
            GetUserNameController.class,
            GetUserListController.class,
            GetUserRatingsController.class);

    /**
     * The generated OpenAPI specification.
     */
    private static final OpenAPI OPENAPI = initOpenApi();

    /**
     * Whether the endpoints of this class are exposed. Swagger is a
     * development-only feature: the flag is read once from the
     * SWAGGER_ENABLED environment variable (or the config.properties file)
     * through the same PropertiesReader used by the messaging components,
     * and, unless it is true, every endpoint of this class answers 404.
     */
    private static final boolean SWAGGER_ENABLED = isSwaggerEnabled();

    /**
     * Resolves the swagger flag from the configuration.
     *
     * @return true only when the flag is explicitly set to true
     */
    private static boolean isSwaggerEnabled() {
        try {
            String value = new PropertiesReader("config.properties").getProperty("swagger.enabled");
            return "true".equalsIgnoreCase(value);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Initializes the OpenAPI specification from the resource classes.
     *
     * @return the generated OpenAPI specification
     */
    private static OpenAPI initOpenApi() {
        SwaggerConfiguration config = new SwaggerConfiguration()
                .openAPI(new OpenAPI()
                        .info(new io.swagger.v3.oas.models.info.Info()
                                .title("SegundUM - User Service API")
                                .description("REST API for managing users in the marketplace")
                                .version("1.0.0"))
                        .servers(List.of(new Server().url("/api"))))
                .prettyPrint(true);

        Reader reader = new Reader(config);
        OpenAPI openAPI = null;
        for (Class<?> cls : RESOURCE_CLASSES) {
            openAPI = reader.read(cls);
        }
        addDefaultErrorResponses(openAPI);
        return openAPI;
    }

    /**
     * Declares the internal server error response for every documented operation, so that the
     * same 500 does not have to be repeated endpoint by endpoint.
     *
     * @param openAPI the generated OpenAPI specification
     */
    private static void addDefaultErrorResponses(OpenAPI openAPI) {
        if (openAPI == null || openAPI.getPaths() == null) {
            return;
        }
        openAPI.getPaths().forEach((path, pathItem) ->
                pathItem.readOperations().forEach(operation -> {
                    if (operation.getResponses() == null) {
                        operation.setResponses(new io.swagger.v3.oas.models.responses.ApiResponses());
                    }
                    operation.getResponses().put("500", internalServerError());
                }));
    }

    /**
     * Builds the shared OpenAPI model of the internal server error response.
     *
     * @return the OpenAPI model for the 500 response
     */
    private static io.swagger.v3.oas.models.responses.ApiResponse internalServerError() {
        io.swagger.v3.oas.models.media.Schema<Object> schema =
                new io.swagger.v3.oas.models.media.Schema<>();
        schema.set$ref("#/components/schemas/ErrorResponse");
        io.swagger.v3.oas.models.media.MediaType json = new io.swagger.v3.oas.models.media.MediaType();
        json.setSchema(schema);
        io.swagger.v3.oas.models.media.Content content = new io.swagger.v3.oas.models.media.Content();
        content.addMediaType(MediaType.APPLICATION_JSON, json);
        io.swagger.v3.oas.models.responses.ApiResponse response =
                new io.swagger.v3.oas.models.responses.ApiResponse();
        response.setDescription("Internal server error");
        response.setContent(content);
        return response;
    }

    /**
     * Serves the OpenAPI specification as JSON.
     *
     * @return the response containing the OpenAPI JSON document
     */
    @GET
    @Path("openapi.json")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getOpenApiJson() {
        if (!SWAGGER_ENABLED) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(Json.pretty(OPENAPI)).build();
    }

    /**
     * Serves the Swagger UI page.
     *
     * @return the response containing the Swagger UI HTML page
     */
    @GET
    @Path("swagger-ui")
    @Produces(MediaType.TEXT_HTML)
    @PermitAll
    public Response swaggerUi() {
        if (!SWAGGER_ENABLED) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        InputStream html = getClass().getClassLoader()
                .getResourceAsStream("swagger-ui/index.html");
        if (html == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(html).build();
    }
}

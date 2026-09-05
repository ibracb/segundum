package segundum.infrastructure.rest.config;

import java.io.InputStream;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.jaxrs2.Reader;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import segundum.infrastructure.rest.user.controllers.DeactivateUserController;
import segundum.infrastructure.rest.user.controllers.GetUserListController;
import segundum.infrastructure.rest.user.controllers.GetUserNameController;
import segundum.infrastructure.rest.user.controllers.GetUserProfileController;
import segundum.infrastructure.rest.user.controllers.GetUserStatsController;
import segundum.infrastructure.rest.user.controllers.RegisterUserController;
import segundum.infrastructure.rest.user.controllers.UpdateUserProfileController;

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
            RegisterUserController.class,
            GetUserProfileController.class,
            GetUserStatsController.class,
            UpdateUserProfileController.class,
            DeactivateUserController.class,
            GetUserNameController.class,
            GetUserListController.class);

    /**
     * The generated OpenAPI specification.
     */
    private static final OpenAPI OPENAPI = initOpenApi();

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
                                .version("1.0.0")))
                .prettyPrint(true);

        Reader reader = new Reader(config);
        OpenAPI openAPI = null;
        for (Class<?> cls : RESOURCE_CLASSES) {
            openAPI = reader.read(cls);
        }
        return openAPI;
    }

    /**
     * Serves the OpenAPI specification as JSON.
     *
     * @return the response containing the OpenAPI JSON document
     */
    @GET
    @Path("openapi.json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpenApiJson() {
        return Response.ok(Json.pretty(OPENAPI)).build();
    }

    /**
     * Serves the OpenAPI specification as YAML.
     *
     * @return the response containing the OpenAPI YAML document
     */
    @GET
    @Path("openapi.yaml")
    @Produces("application/yaml")
    public Response getOpenApiYaml() {
        return Response.ok(Yaml.pretty(OPENAPI)).build();
    }

    /**
     * Serves the Swagger UI page.
     *
     * @return the response containing the Swagger UI HTML page
     */
    @GET
    @Path("swagger-ui")
    @Produces(MediaType.TEXT_HTML)
    public Response swaggerUi() {
        InputStream html = getClass().getClassLoader()
                .getResourceAsStream("swagger-ui/index.html");
        if (html == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(html).build();
    }
}

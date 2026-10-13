package segundum.infrastructure.rest.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Serves the landing page of the Swagger UI of this service.
 *
 * <p>The index.html bundled inside the swagger-ui webjar hard-codes the Petstore
 * example as its document, so the page opened at /swagger-ui/index.html would
 * always show that example instead of this API. This controller takes over the
 * Swagger UI entry paths (/swagger-ui, /swagger-ui/ and /swagger-ui/index.html)
 * and renders the classpath template with the document of this service (context
 * path included), while every other asset keeps being served by the springdoc
 * resource handler.</p>
 *
 * <p>The whole controller is only registered when swagger.enabled is true, so
 * that in production (default) the Swagger UI is not exposed at all: with
 * springdoc disabled as well, /swagger-ui and /v3/api-docs return 404.</p>
 */
@Hidden
@RestController
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
public class SwaggerUiController {

    /**
     * Template of the landing page, rendered on every request with the title
     * and the context path of this service.
     */
    private static final String TEMPLATE = loadTemplate();

    /**
     * Title of this service, shown in the browser tab.
     */
    private static final String TITLE = "SegundUM - Sale Service API";

    /**
     * Renders the landing page of the Swagger UI pointing at the OpenAPI
     * document of this service.
     *
     * @param request the current request, used to resolve the context path
     * @return the rendered landing page
     */
    @GetMapping(value = { "/swagger-ui/index.html", "/swagger-ui", "/swagger-ui/" },
            produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> index(HttpServletRequest request) {
        String html = TEMPLATE
                .replace("{{TITLE}}", TITLE)
                .replace("{{BASE_HREF}}", request.getContextPath() + "/swagger-ui/")
                .replace("{{API_DOCS_URL}}", request.getContextPath() + "/v3/api-docs");
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }

    /**
     * Reads the landing page template from the classpath.
     *
     * @return the raw content of the template
     */
    private static String loadTemplate() {
        try (InputStream stream = new ClassPathResource("swagger-ui/index.html").getInputStream()) {
            return StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read the template /swagger-ui/index.html", e);
        }
    }

}

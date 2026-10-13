package segundum.infrastructure.security;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

import com.google.gson.Gson;

import segundum.infrastructure.rest.errors.responses.ErrorResponse;

/**
 * JAX-RS filter that validates JWT tokens on incoming requests.
 * <p>
 * If the resource method is annotated with {@code @PermitAll}, the filter is skipped.
 * Otherwise, a valid Bearer token must be present in the Authorization header or cookie.
 * </p>
 */
@Provider
public class JwtTokenFilter implements ContainerRequestFilter {

    private final JwtUtils jwtUtils;

    /**
     * Serializer of the error bodies of the platform.
     */
    private static final Gson GSON = new Gson();

    @Context
    private ResourceInfo resourceInfo;

    public JwtTokenFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method resourceMethod = resourceInfo.getResourceMethod();

        if (resourceMethod != null && resourceMethod.isAnnotationPresent(javax.annotation.security.PermitAll.class)) {
            return;
        }

        String path = requestContext.getUriInfo().getPath();
        if (path.endsWith("/authenticate")) {
            return;
        }

        String token = null;

        String authorizationHeader = requestContext.getHeaderString("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        } else {
            String cookieHeader = requestContext.getHeaderString("Cookie");
            if (cookieHeader != null) {
                for (String part : cookieHeader.split(";")) {
                    String[] kv = part.trim().split("=", 2);
                    if (kv.length == 2 && "access_token".equals(kv[0].trim())) {
                        token = kv[1].trim();
                        break;
                    }
                }
            }
        }

        if (token == null) {
            requestContext.abortWith(unauthorized("Missing or invalid access token"));
            return;
        }

        try {
            Claims claims = jwtUtils.validateToken(token);
            String userId = jwtUtils.extractUserId(claims);
            List<String> roles = jwtUtils.extractRoles(claims);

            requestContext.setProperty("userId", userId);
            requestContext.setProperty("roles", roles);

            if (resourceMethod != null && resourceMethod.isAnnotationPresent(javax.annotation.security.RolesAllowed.class)) {
                javax.annotation.security.RolesAllowed rolesAllowed =
                        resourceMethod.getAnnotation(javax.annotation.security.RolesAllowed.class);
                String[] allowedRoles = rolesAllowed.value();

                boolean hasAllowedRole = roles.stream()
                        .anyMatch(role -> Arrays.asList(allowedRoles).contains(role));
                if (!hasAllowedRole) {
                    requestContext.abortWith(forbidden("Insufficient permissions"));
                    return;
                }
            }

            String pathId = extractIdFromPath(path);
            String method = requestContext.getMethod();
            if (pathId != null && !pathId.equals(userId)
                    && !"GET".equals(method)) {
                requestContext.abortWith(forbidden("Access denied"));
                return;
            }

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
            requestContext.abortWith(unauthorized("Invalid JWT token"));
        }
    }

    /**
     * Extracts the {id} from the URL path.
     */
    private String extractIdFromPath(String path) {
        String[] parts = path.split("/");
        for (int i = 0; i < parts.length; i++) {
            if ("users".equals(parts[i]) && i + 1 < parts.length) {
                String id = parts[i + 1];
                if (!"name".equals(id) && !"stats".equals(id) && !"profile".equals(id)
                        && !"authenticate".equals(id)) {
                    return id;
                }
            }
        }
        return null;
    }

    /**
     * Builds the 401 response with the error body of the platform.
     */
    private static Response unauthorized(String message) {
        return error(Response.Status.UNAUTHORIZED, message);
    }

    /**
     * Builds the 403 response with the error body of the platform.
     */
    private static Response forbidden(String message) {
        return error(Response.Status.FORBIDDEN, message);
    }

    /**
     * Builds the JSON error body shared by the services of the platform:
     * status code, reason phrase and message.
     */
    private static Response error(Response.Status status, String message) {
        ErrorResponse error = new ErrorResponse(
                status.getStatusCode(), status.getReasonPhrase(), message);
        return Response.status(status)
                .entity(GSON.toJson(error))
                .type("application/json")
                .build();
    }

}

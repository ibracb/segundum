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

    @Context
    private ResourceInfo resourceInfo;

    public JwtTokenFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method resourceMethod = resourceInfo.getResourceMethod();

        // Check for @PermitAll annotation
        if (resourceMethod != null && resourceMethod.isAnnotationPresent(javax.annotation.security.PermitAll.class)) {
            return;
        }

        // Skip auth for authenticate endpoint
        String path = requestContext.getUriInfo().getPath();
        if (path.endsWith("/authenticate")) {
            return;
        }

        // Extract token from header or cookie
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
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("{\"error\": \"Missing or invalid Authorization header\"}")
                            .type("application/json")
                            .build());
            return;
        }

        try {
            Claims claims = jwtUtils.validateToken(token);
            String userId = jwtUtils.extractUserId(claims);
            List<String> roles = jwtUtils.extractRoles(claims);

            // Store user info in request attributes for controllers
            requestContext.setProperty("userId", userId);
            requestContext.setProperty("roles", roles);

            // Check @RolesAllowed annotation
            if (resourceMethod != null && resourceMethod.isAnnotationPresent(javax.annotation.security.RolesAllowed.class)) {
                javax.annotation.security.RolesAllowed rolesAllowed =
                        resourceMethod.getAnnotation(javax.annotation.security.RolesAllowed.class);
                String[] allowedRoles = rolesAllowed.value();

                boolean hasAllowedRole = roles.stream()
                        .anyMatch(role -> Arrays.asList(allowedRoles).contains(role));
                if (!hasAllowedRole) {
                    requestContext.abortWith(
                            Response.status(Response.Status.FORBIDDEN)
                                    .entity("{\"error\": \"Insufficient permissions\"}")
                                    .type("application/json")
                                    .build());
                    return;
                }
            }

            // Ownership check: modify operations require owner
            String pathId = extractIdFromPath(path);
            String method = requestContext.getMethod();
            if (pathId != null && !pathId.equals(userId)
                    && !"GET".equals(method)) {
                requestContext.abortWith(
                        Response.status(Response.Status.FORBIDDEN)
                                .entity("{\"error\": \"Access denied\"}")
                                .type("application/json")
                                .build());
                return;
            }

        } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("{\"error\": \"Invalid JWT token\"}")
                            .type("application/json")
                            .build());
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

}

package segundum.infrastructure.security;

import java.util.Arrays;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Utility class for JWT token validation.
 * This service only validates tokens; it does not generate them.
 */
public class JwtUtils {

    /**
     * The signing secret for JWT tokens.
     */
    private final String secret;

    /**
     * Constructs a new JwtUtils with the given secret.
     *
     * @param secret the signing secret
     */
    public JwtUtils(String secret) {
        this.secret = secret;
    }

    /**
     * Validates a JWT token and returns its claims.
     *
     * @param token the JWT token to validate
     * @return the claims from the token
     */
    public Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extracts the user identifier from the token claims.
     *
     * @param claims the token claims
     * @return the user identifier (subject)
     */
    public String extractUserId(Claims claims) {
        return claims.getSubject();
    }

    /**
     * Extracts the roles from the token claims.
     *
     * @param claims the token claims
     * @return the list of role names
     */
    public List<String> extractRoles(Claims claims) {
        String rolesClaim = claims.get("roles", String.class);
        if (rolesClaim == null || rolesClaim.isEmpty()) {
            return Arrays.asList();
        }
        return Arrays.asList(rolesClaim.split(","));
    }

}

package segundum.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Utility class for JWT token validation.
 * This service only validates tokens; it does not generate them.
 */
@Component
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
    public JwtUtils(@Value("${jwt.secret}") String secret) {
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
     * Extracts the roles from the token claims as a comma-separated string.
     *
     * @param claims the token claims
     * @return the roles string (e.g., "USER,ADMINISTRATOR")
     */
    public String extractRoles(Claims claims) {
        return claims.get("roles", String.class);
    }

}

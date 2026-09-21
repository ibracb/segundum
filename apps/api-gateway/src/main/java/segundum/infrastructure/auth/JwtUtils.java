package segundum.infrastructure.auth;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Utility class for JWT token generation and validation.
 */
@Component
public class JwtUtils {

    /**
     * The signing secret for JWT tokens.
     */
    private final String secret;

    /**
     * The token expiration time in milliseconds.
     */
    private final long expiration;

    /**
     * Constructs a new JwtUtils with the configured secret and expiration.
     *
     * @param secret     the signing secret
     * @param expiration the expiration time in milliseconds
     */
    public JwtUtils(@Value("${jwt.secret}") String secret,
                    @Value("${jwt.expiration}") long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    /**
     * Generates a JWT token for the given user data.
     *
     * @param id       the user identifier (subject)
     * @param fullName the user full name
     * @param roles    the user roles
     * @return the signed JWT token
     */
    public String generateToken(String id, String fullName, List<String> roles) {
        Claims claims = Jwts.claims()
                .setSubject(id);
        claims.put("fullName", fullName);
        claims.put("roles", roles.stream().collect(Collectors.joining(",")));
        Date now = new Date();
        Date expiryDate = Date.from(Instant.now().plusSeconds(expiration));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Validates a JWT token.
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

}

package segundum.infrastructure.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * JWT authentication filter that intercepts every request,
 * extracts and validates the Bearer token, and sets the
 * authentication in the SecurityContext.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    /**
     * The JWT utility.
     */
    private final JwtUtils jwtUtils;

    /**
     * Constructs a new JwtRequestFilter with the given JWT utility.
     *
     * @param jwtUtils the JWT utility
     */
    public JwtRequestFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("access_token".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }

        if (token != null) {
            try {
                Claims claims = jwtUtils.validateToken(token);
                String userId = jwtUtils.extractUserId(claims);
                String rolesClaim = claims.get("roles", String.class);

                List<SimpleGrantedAuthority> authorities = Arrays
                        .stream(rolesClaim.split(","))
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                        .collect(Collectors.toList());

                List<SimpleGrantedAuthority> allAuthorities = new java.util.ArrayList<>(authorities);
                Arrays.stream(rolesClaim.split(","))
                        .map(role -> new SimpleGrantedAuthority(role.trim()))
                        .forEach(allAuthorities::add);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId, null, allAuthorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
            }
        }

        filterChain.doFilter(request, response);
    }

}

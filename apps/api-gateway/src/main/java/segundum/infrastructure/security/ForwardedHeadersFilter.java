package segundum.infrastructure.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Removes the proxy headers sent by the client before any other component
 * reads them.
 *
 * The API gateway is the only component allowed to state where a request came
 * from. Zuul adds its own X-Forwarded-* values when forwarding, and the
 * downstream services read them to build the absolute URLs they publish. A
 * header injected by a client would travel next to the one of the gateway
 * instead of replacing it, so the services would receive both: some publish
 * the value of the client, and others discard the whole pair as malformed and
 * fall back to the internal host of the container.
 *
 * Dropping the headers here is the only way to tell the two apart: once they
 * have been merged, no later component can know which one the client wrote.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ForwardedHeadersFilter extends OncePerRequestFilter {

    /**
     * The headers that only the gateway is allowed to state. Whatever the
     * client sends under these names is discarded.
     */
    private static final Set<String> OWNED_HEADERS = Set.of(
            "forwarded",
            "x-forwarded-host",
            "x-forwarded-port",
            "x-forwarded-proto",
            "x-forwarded-for");

    /**
     * Runs the filter chain, hiding the headers of the gateway from the rest
     * of the chain when the client has sent any of them.
     *
     * @param request     the incoming request
     * @param response    the response
     * @param filterChain the rest of the chain, which includes Zuul
     * @throws ServletException if the chain fails
     * @throws IOException      if the chain fails
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (hasForwardedHeaders(request)) {
            filterChain.doFilter(new ForwardedHeadersRequestWrapper(request), response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Tells whether the client has sent any of the headers the gateway owns.
     * The common case sends none, and then the request is not wrapped at all.
     *
     * @param request the incoming request
     * @return true when at least one of those headers is present
     */
    private boolean hasForwardedHeaders(HttpServletRequest request) {
        for (String header : OWNED_HEADERS) {
            if (request.getHeader(header) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hides the headers of the gateway from the rest of the chain, leaving
     * every other header, such as Authorization, untouched.
     */
    private static class ForwardedHeadersRequestWrapper extends HttpServletRequestWrapper {

        /**
         * Constructs a new wrapper for the given request.
         *
         * @param request the request to hide the headers from
         */
        ForwardedHeadersRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        /**
         * Returns the value of a header, or null when the gateway owns it.
         *
         * @param name the header name
         * @return the value of the header, or null
         */
        @Override
        public String getHeader(String name) {
            return isOwned(name) ? null : super.getHeader(name);
        }

        /**
         * Returns every value of a header, empty when the gateway owns it.
         *
         * @param name the header name
         * @return the values of the header
         */
        @Override
        public Enumeration<String> getHeaders(String name) {
            return isOwned(name)
                    ? Collections.emptyEnumeration()
                    : super.getHeaders(name);
        }

        /**
         * Returns the names of every header of the request, without the ones
         * the gateway owns.
         *
         * @return the names of the headers
         */
        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = new ArrayList<>();

            for (String name : Collections.list(super.getHeaderNames())) {
                if (!isOwned(name)) {
                    names.add(name);
                }
            }

            return Collections.enumeration(names);
        }

        /**
         * Tells whether the gateway owns the given header.
         *
         * @param name the header name
         * @return true when the header belongs to the gateway
         */
        private static boolean isOwned(String name) {
            return name != null && OWNED_HEADERS.contains(name.toLowerCase(Locale.ROOT));
        }

    }

}

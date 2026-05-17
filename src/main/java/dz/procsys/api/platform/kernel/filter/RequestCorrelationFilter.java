/**
 *	
 *	@Author		: CHOUABBIA Amine
 *
 *	@Name		: RequestCorrelationFilter
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 05-17-2026
 *
 *	@Type		: Class
 *	@Layer		: Template
 *	@Package	: Platform / Kernel
 *
 **/

package dz.procsys.api.platform.kernel.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.UUID;

/**
 * Servlet filter that ensures every incoming request has a correlation identifier.
 * The identifier can be propagated to logs and downstream services for tracing.
 */
public class RequestCorrelationFilter implements Filter {

    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            String correlationId = httpRequest.getHeader(CORRELATION_ID_HEADER);
            if (correlationId == null || correlationId.isBlank()) {
                correlationId = UUID.randomUUID().toString();
            }
            // Typically stored in MDC or similar logging context here.
            // MDC.put(CORRELATION_ID_HEADER, correlationId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            // MDC.clear(); // if MDC is used
        }
    }
}
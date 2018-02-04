package ru.elazarev.filters;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for user authorisation.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    /**
     * Name of user attribute in session.
     */
    public static final String ATTRIBUTE_USER = "user";

    /**
     * Empty init method.
     * @param filterConfig filter configuration.
     * @throws ServletException if some errors occur.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Process authorisation filter.
     * @param servletRequest request.
     * @param servletResponse response.
     * @param filterChain next filter.
     * @throws IOException if some errors occur.
     * @throws ServletException  if some errors occur.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) servletRequest);
        HttpSession session = req.getSession();
        Object user = session.getAttribute(ATTRIBUTE_USER);
        String uri = req.getRequestURI();
        if (user == null && !uri.endsWith("login")) {
            req.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Empty destroy method.
     */
    @Override
    public void destroy() {
    }
}

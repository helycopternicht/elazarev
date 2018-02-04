package ru.elazarev.filters;

import ru.elazarev.models.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filters all request to users if you don't have admin permission.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.02.18
 */
@WebFilter("/users/*")
public class UsersFilter implements Filter {
    /**
     * Empty init method.
     * @param filterConfig config.
     * @throws ServletException if error occur.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Filters request. If u don't have admin permissions.
     * @param servletRequest request.
     * @param servletResponse response.
     * @param filterChain next filter.
     * @throws IOException if error occur.
     * @throws ServletException if error occur.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) session.getAttribute(AuthorizationFilter.ATTRIBUTE_USER);
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        if (!user.isAdmin()) {
            resp.sendError(403, "You have no permissions");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Empty destroy method.
     */
    @Override
    public void destroy() {
    }
}

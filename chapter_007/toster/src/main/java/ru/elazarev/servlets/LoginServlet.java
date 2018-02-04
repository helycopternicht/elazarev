package ru.elazarev.servlets;

import ru.elazarev.database.ConnectionFactory;
import ru.elazarev.filters.AuthorizationFilter;
import ru.elazarev.models.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet to login users.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    /**
     * Empty init method.
     * @throws ServletException if error occur.
     */
    @Override
    public void init() throws ServletException {
    }

    /**
     * Process login.
     * @param request request.
     * @param response response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pwd = request.getParameter("password");
        HttpSession session = request.getSession();

        EntityManager em = ConnectionFactory.getFactory().createEntityManager();

        User user = null;
        try {
            user = em.createQuery("select u from User u where u.login = :login and u.password = :password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", pwd)
                    .getSingleResult();
        } catch (Exception ex) {
            /*кто то ввел неверные данные*/
            response.sendRedirect("login");
            return;
        } finally {
            em.close();
        }

        session.setAttribute(AuthorizationFilter.ATTRIBUTE_USER, user);
        response.sendRedirect(request.getContextPath() + "/");
    }

    /**
     * Shows login form.
     * @param request request.
     * @param response response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

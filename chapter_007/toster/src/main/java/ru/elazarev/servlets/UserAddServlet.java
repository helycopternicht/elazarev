package ru.elazarev.servlets;

import ru.elazarev.database.ConnectionFactory;
import ru.elazarev.models.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to add users.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.02.18
 */
@WebServlet("/users/add")
public class UserAddServlet extends HttpServlet {
    /**
     * Show add user form.
     * @param req request.
     * @param resp response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
    }

    /**
     * Adds new user to app.
     * @param req request.
     * @param resp response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String isAdmin = req.getParameter("isAdmin");

        if ("".equals(login) || "".equals(password) || "".equals(email)) {
            req.getRequestDispatcher("/addUser.jsp").forward(req, resp);
            return;
        }

        User newUser = new User(login, password, email, isAdmin == null ? false : true);

        EntityManager em = ConnectionFactory.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(newUser);
        em.getTransaction().commit();
        em.close();

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}

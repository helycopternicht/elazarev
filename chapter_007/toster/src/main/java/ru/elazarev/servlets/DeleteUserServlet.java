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
 * Servlet for remove user from bd.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 03.02.18
 */
@WebServlet("/users/remove")
public class DeleteUserServlet extends HttpServlet {
    /**
     * Process user removing.
     * @param req request.
     * @param resp response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringID = req.getParameter("id");
        int id = 0;
        try {
            id = Integer.valueOf(stringID);
        } catch (NumberFormatException ex) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        EntityManager em = ConnectionFactory.getFactory().createEntityManager();
        User user = em.find(User.class, id);
        if (user == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}

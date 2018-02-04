package ru.elazarev.servlets;

import ru.elazarev.database.ConnectionFactory;
import ru.elazarev.models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Show user list.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.02.18
 */
@WebServlet("/users")
public class UsersListServlet extends HttpServlet {
    /**
     * Show users list.
     * @param req request.
     * @param resp response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = ConnectionFactory.getFactory().createEntityManager();
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        List<User> list = query.getResultList();
        em.close();

        req.setAttribute("users", list);
        req.getRequestDispatcher("/userList.jsp").forward(req, resp);
    }
}

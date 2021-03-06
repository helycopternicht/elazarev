package ru.elazarev.servlets;

import ru.elazarev.database.ConnectionFactory;
import ru.elazarev.models.Question;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to show questions list.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {

    /**
     * Shows questions list..
     * @param req request.
     * @param resp response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = ConnectionFactory.getFactory().createEntityManager();
        List<Question> list = em.createQuery("select q from Question q", Question.class).getResultList();
        em.close();

        req.setAttribute("questions", list);
        req.getRequestDispatcher("questions.jsp").forward(req, resp);
    }

    /**
     * Shows questions list..
     * @param req request.
     * @param resp response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

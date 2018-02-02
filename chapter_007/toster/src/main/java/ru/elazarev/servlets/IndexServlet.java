package ru.elazarev.servlets;

import ru.elazarev.database.EntityManagerF;
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
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    /**
     * 123.
     * @par1am req 123.
     * @param resp 123.
     * @throws ServletException 123.
     * @throws IOException 123.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = EntityManagerF.getFactory().createEntityManager();
        List<Question> list = em.createQuery("select q from Question q", Question.class).getResultList();
        em.close();

        req.setAttribute("questions", list);
        req.getRequestDispatcher("questions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

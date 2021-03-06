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

/**
 * Show question info and answer form.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.01.18
 */
@WebServlet("/viewQuestion")
public class ViewQuestionServlet extends HttpServlet {
    /**
     * Get data from database and show from.
     * @param req request.
     * @param resp response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");
        int id = 0;
        try {
            id = Integer.valueOf(stringId);
        } catch (NumberFormatException ex) {
            resp.sendError(404);
            return;
        }

        EntityManager em = ConnectionFactory.getFactory().createEntityManager();
        Question question = em.find(Question.class, id);
        if (question == null) {
            resp.sendError(404);
            return;
        }
        em.close();

        req.setAttribute("question", question);
        req.setAttribute("answers", question.getAnswers());

        req.getRequestDispatcher("viewQuestion.jsp").forward(req, resp);
    }
}

package ru.elazarev.servlets;

import ru.elazarev.models.Answer;
import ru.elazarev.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for add answer to question.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.01.18
 */
@WebServlet("/addAnswer")
public class AddAnswerServlet extends HttpServlet {
    /**
     * Adds answer to question.
     * @param req request.
     * @param resp response.
     * @throws ServletException if error occur.
     * @throws IOException if error occur.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        String text = req.getParameter("text");
        String stringId = req.getParameter("questionId");
        String questionURL = req.getHeader("referer");

        if ("".equals(text) || "".equals(stringId)) {
            resp.sendRedirect(questionURL);
            return;
        }

        int id = 0;
        try {
            id = Integer.valueOf(stringId);
        } catch (Exception ex) {
            resp.sendError(404);
            return;
        }

        Answer.addAnswer(user, text, id);

        resp.sendRedirect(questionURL);
    }
}

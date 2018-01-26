package ru.elazarev.servlets;

import ru.elazarev.models.AnswerModel;
import ru.elazarev.models.CategoryModel;
import ru.elazarev.models.QuestionModel;
import ru.elazarev.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.01.18
 */
@WebServlet(urlPatterns = "/viewQuestion")
public class ViewQuestionServlet extends HttpServlet {

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

        UserModel user = (UserModel) req.getSession().getAttribute("user");

        UserModel u = new UserModel(1, "Admin", "123", "gemauilk", true);
        CategoryModel javaCat = new CategoryModel(1, "java");
        CategoryModel htmlCat = new CategoryModel(1, "html");


        List<QuestionModel> list = new ArrayList<>();
        list.add(new QuestionModel(
                1,
                u,
                LocalDateTime.now(),
                "How to 1",
                Arrays.asList(javaCat, htmlCat),
                "totototototot",
                null,
                null));

        AnswerModel answer = new AnswerModel(1, 2, u, LocalDateTime.now(), "Сам разобрался");

        list.add(new QuestionModel(
                2,
                u,
                LocalDateTime.now(),
                "How to java",
                Arrays.asList(javaCat),
                "Чет не могу сообразить! Подскажите!",
                Arrays.asList(answer),
                answer));

        // code for getting question by id.
        QuestionModel question = null;
        for (QuestionModel q : list) {
            if (q.getId() == id) {
                question = q;
            }
        }
        req.setAttribute("question", question);
        req.setAttribute("answers", question.getAnswers());

        req.getRequestDispatcher("viewQuestion.jsp").forward(req, resp);
    }
}

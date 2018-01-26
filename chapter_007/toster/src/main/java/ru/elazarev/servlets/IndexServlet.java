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
 * @since 24.01.18
 */
@WebServlet(urlPatterns = "/")
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
        List<QuestionModel> list;
        UserModel u = new UserModel(1, "Admin", "123", "gemauilk", true);
        CategoryModel javaCat = new CategoryModel(1, "java");
        CategoryModel htmlCat = new CategoryModel(1, "html");


        list = new ArrayList<>();
        list.add(new QuestionModel(
                1,
                u,
                LocalDateTime.now(),
                "How to 1",
                Arrays.asList(javaCat, htmlCat),
                "totototototot",
                new ArrayList<AnswerModel>(),
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


        req.setAttribute("questions", list);
        req.getRequestDispatcher("questions.jsp").forward(req, resp);
    }
}

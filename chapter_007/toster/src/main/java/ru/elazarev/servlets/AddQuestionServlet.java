package ru.elazarev.servlets;

import ru.elazarev.models.CategoryModel;
import ru.elazarev.models.QuestionModel;
import ru.elazarev.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.01.18
 */
@WebServlet(urlPatterns = "/addQuestion")
public class AddQuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String stringCategorys = req.getParameter("category");

        List<CategoryModel> categorys = new ArrayList<>();
        for (String cat : stringCategorys.split(";")) {
            new CategoryModel(1, cat);
        }

        QuestionModel q = new QuestionModel(6, (UserModel) session.getAttribute("user"),
                LocalDateTime.now(), title, categorys, description, null, null);

        resp.getWriter().append(q.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addQuestion.jsp").forward(req, resp);
    }
}

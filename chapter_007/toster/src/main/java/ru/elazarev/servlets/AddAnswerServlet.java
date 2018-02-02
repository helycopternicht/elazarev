package ru.elazarev.servlets;

import ru.elazarev.database.EntityManagerF;
import ru.elazarev.models.Answer;
import ru.elazarev.models.Question;
import ru.elazarev.models.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.01.18
 */
@WebServlet("/addAnswer")
public class AddAnswerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        String text = req.getParameter("text");
        String stringId = req.getParameter("questionId");
        String questionURL = req.getHeader("referer");

//        String test = text + " " + stringId;
//        System.out.println(test);
//        resp.getWriter().append(test);

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

        EntityManager em = EntityManagerF.getFactory().createEntityManager();
        em.getTransaction().begin();

        Question question = em.find(Question.class, id);
        if (question != null) {
            Answer answer = new Answer();
            answer.setAuthor(user);
            answer.setCreatedDate(LocalDateTime.now());
            answer.setText(text);
            answer.setQuestion(question);
            em.persist(answer);
            em.getTransaction().commit();

        } else {
            em.getTransaction().rollback();
        }
        em.close();

        resp.sendRedirect(questionURL);
    }
}

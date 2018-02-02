package ru.elazarev.servlets;

import ru.elazarev.database.EntityManagerF;
import ru.elazarev.filters.AuthorizationFilter;
import ru.elazarev.models.Category;
import ru.elazarev.models.Question;
import ru.elazarev.models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        User user = (User) session.getAttribute(AuthorizationFilter.ATTRIBUTE_USER);

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String stringCategorys = req.getParameter("category");

        EntityManager em = EntityManagerF.getFactory().createEntityManager();

        List<Category> categorys = new ArrayList<>();
        Category c = null;

        em.getTransaction().begin();
        Question question = new Question(user, LocalDateTime.now(), title, description);
        em.persist(question);

        for (String name : stringCategorys.split(";")) {
            Query query = em.createQuery("select c from Category c where c.name = :name");
            query.setParameter("name", name);
            try {
                c = (Category) query.getSingleResult();
            } catch (Exception ex) {
                c = new Category(name);
                em.persist(c);
            }
            question.addCategory(c);
        }
        em.persist(question);
        em.getTransaction().commit();
        em.close();
        resp.sendRedirect("viewQuestion?id=" + question.getId());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addQuestion.jsp").forward(req, resp);
    }
}

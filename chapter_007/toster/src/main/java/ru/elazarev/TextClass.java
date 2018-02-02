package ru.elazarev;

import ru.elazarev.models.Answer;
import ru.elazarev.models.Category;
import ru.elazarev.models.Question;
import ru.elazarev.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.01.18
 */
public class TextClass {

    public static void main(String[] args) {

        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("primary");
        EntityManager em = sessionFactory.createEntityManager();

        List<Question> list = em.createQuery("select q from Question q", Question.class).getResultList();
        int n = 0;
        em.close();
        sessionFactory.close();
    }
}

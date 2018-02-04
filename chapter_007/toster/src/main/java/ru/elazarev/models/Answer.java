package ru.elazarev.models;

import ru.elazarev.database.ConnectionFactory;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.EntityManager;

import java.time.LocalDateTime;

/**
 * Answer java bean.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@Entity
@Table(name = "answers")
public class Answer {
    /**
     * Answer id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    /**
     * Question of this answer.
     */
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    /**
     * Answer author.
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    /**
     * Create date.
     */
    @Column(name = "create_date")
    private LocalDateTime createdDate;
    /**
     * Answers text.
     */
    @Column(name = "text")
    private String text;
    /**
     * Is answer solution.
     */
    @Column(name = "solution")
    private boolean solution;

    /**
     * Default constructor.
     */
    public Answer() {
    }

    /**
     * Parametrized constructor.
     * @param question question.
     * @param author author.
     * @param text text.
     */
    public Answer(Question question, User author, String text) {
        this.question = question;
        this.author = author;
        this.createdDate = LocalDateTime.now();
        this.text = text;
    }

    /**
     * Getter for id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for question field.
     * @return question.
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Setter for question field.
     * @param question new question.
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * Getter for author field.
     * @return author.
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Setter for author field.
     * @param author new author.
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Getter for create date field.
     * @return create date.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Setter for create date field.
     * @param createdDate new create date.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Getter for text field.
     * @return text.
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for text field.
     * @param text new text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for solution field.
     * @return solution.
     */
    public boolean isSolution() {
        return solution;
    }

    /**
     * Setter for solution field.
     * @param solution new solution.
     */
    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    /**
     * Adds answer to database.
     * @param user author.
     * @param text answer text.
     * @param questionId question id.
     */
    public static void addAnswer(User user, String text, int questionId) {
        EntityManager em = ConnectionFactory.getFactory().createEntityManager();
        em.getTransaction().begin();

        Question question = em.find(Question.class, questionId);
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
    }
}

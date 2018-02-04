package ru.elazarev.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Question java bean. Central object of app.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
@Entity
@Table(name = "questions")
public class Question {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    /**
     * Question author.
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    /**
     * Create date.
     */
    @Column(name = "create_date")
    private LocalDateTime createDate;
    /**
     * Question title.
     */
    @Column(name = "title")
    private String title;
    /**
     * Questions description.
     */
    @Column(name = "description")
    private String description;
    /**
     * Question answers.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private List<Answer> answers;
    /**
     * Question categorys.
     */
    @ManyToMany
    @JoinTable(name = "questions_categorys",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categorys;

    /**
     * Default constructor.
     */
    public Question() {
    }

    /**
     * Parametrized constructor.
     * @param author question author.
     * @param title title.
     * @param description description.
     */
    public Question(User author, String title, String description) {
        this.author = author;
        this.createDate = LocalDateTime.now();
        this.title = title;
        this.description = description;
        this.answers = new ArrayList<>();
        this.categorys = new ArrayList<>();
    }

    /**
     * Getter for id field.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id field.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
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
     * Getter for create date.
     * @return create date.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Setter for create date field.
     * @param createDate create date.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for title field.
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title field.
     * @param title new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for category field.
     * @return categorys.
     */
    public List<Category> getCategorys() {
        return categorys;
    }

    /**
     * Setter for category field.
     * @param categorys new category.
     */
    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }

    /**
     * Getter for description field.
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description field.
     * @param description new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for answers list.
     * @return question answers.
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Setter for answers list.
     * @param answers new answers list.
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * Adds category to category list.
     * @param c category.
     */
    public void addCategory(Category c) {
        categorys.add(c);
    }

    /**
     * Adds answer to answers list.
     * @param a answer.
     */
    public void addAnswer(Answer a) {
        answers.add(a);
    }

    /**
     * Returns true if question has solution.
     * @return true of false.
     */
    public boolean hasSolution() {
        for (Answer a : getAnswers()) {
            if (a.isSolution()) {
                return true;
            }
        }
        return false;
    }
}

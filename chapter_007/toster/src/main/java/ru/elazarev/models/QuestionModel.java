package ru.elazarev.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Model for questions.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
public class QuestionModel {

    private int id;

    private UserModel author;

    private LocalDateTime createDate;

    private String title;

    private List<CategoryModel> categorys;

    private String description;

    private List<AnswerModel> answers;

    private AnswerModel solution;

    public QuestionModel(int id, UserModel author, LocalDateTime createDate, String title,
                         List<CategoryModel> categorys, String description,
                         List<AnswerModel> answers, AnswerModel solution) {
        this.id = id;
        this.author = author;
        this.createDate = createDate;
        this.title = title;
        this.categorys = categorys;
        this.description = description;
        this.answers = answers;
        this.solution = solution;
    }

    public QuestionModel(UserModel author, LocalDateTime createDate, String title,
                         List<CategoryModel> categorys, String description,
                         List<AnswerModel> answers, AnswerModel solution) {
        this.author = author;
        this.createDate = createDate;
        this.title = title;
        this.categorys = categorys;
        this.description = description;
        this.answers = answers;
        this.solution = solution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CategoryModel> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryModel> categorys) {
        this.categorys = categorys;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }

    public AnswerModel getSolution() {
        return solution;
    }

    public void setSolution(AnswerModel solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QuestionModel{");
        sb.append("id=").append(id);
        sb.append(", author=").append(author);
        sb.append(", createDate=").append(createDate);
        sb.append(", title='").append(title).append('\'');
        sb.append(", categorys=").append(categorys);
        sb.append(", description='").append(description).append('\'');
        sb.append(", answers=").append(answers);
        sb.append(", solution=").append(solution);
        sb.append('}');
        return sb.toString();
    }
}

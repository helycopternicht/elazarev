package ru.elazarev.models;

import java.time.LocalDateTime;

/**
 * Model for Toster app answers.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 24.01.18
 */
public class AnswerModel {

    private int id;

    private int questionId;

    private UserModel author;

    private LocalDateTime createdDate;

    private String text;

    public AnswerModel(int id, int questionId, UserModel author, LocalDateTime createdDate, String text) {
        this.id = id;
        this.questionId = questionId;
        this.author = author;
        this.createdDate = createdDate;
        this.text = text;
    }

    public AnswerModel(int questionId, UserModel author, LocalDateTime createdDate, String text) {
        this.questionId = questionId;
        this.author = author;
        this.createdDate = createdDate;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

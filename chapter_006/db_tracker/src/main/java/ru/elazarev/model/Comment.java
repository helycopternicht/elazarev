package ru.elazarev.model;

import java.time.LocalDateTime;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 20.12.17
 */
public class Comment {

    private int id;

    private User author;

    private String comment;

    private LocalDateTime createDate;

    public Comment(int id, User author, String comment, LocalDateTime createDate) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", comment='" + comment + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

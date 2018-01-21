package ru.elazarev.model;

import java.time.LocalDateTime;

/**
 * Comment model class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 20.12.17
 */
public class Comment {
    /**
     * Comment id.
     */
    private int id;
    /**
     * Comment author.
     */
    private User author;
    /**
     * Text of comment.
     */
    private String comment;
    /**
     * Creation date.
     */
    private LocalDateTime createDate;
    /**
     * Id of comment request.
     */
    private int requestId;

    /**
     * Default constructor.
     * @param id id.
     * @param author author.
     * @param comment comment.
     * @param createDate creation date.
     * @param requestId user request id.
     */
    public Comment(int id, User author, String comment, LocalDateTime createDate, int requestId) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.createDate = createDate;
        this.requestId = requestId;
    }

    /**
     * Getter for comment id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for comment id.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter.
     * @return author.
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Setter.
     * @param author new author.
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Getter.
     * @return text.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter.
     * @param comment new comment.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter.
     * @return creation date.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Setter.
     * @param createDate new creation date.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter.
     * @return request id.
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * Setter.
     * @param requestId new request id.
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * Default to string method.
     * @return string representation of comment model.
     */
    @Override
    public String toString() {
        return "Comment{"
                + "id=" + id
                + ", author=" + author
                + ", comment='" + comment + '\''
                + ", createDate=" + createDate
                + '}';
    }
}

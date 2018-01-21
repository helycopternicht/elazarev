package ru.elazarev.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Request table model.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 18.12.17
 */
public class UserRequest {
    /**
     * Request id.
     */
    private int id;
    /**
     * Request title.
     */
    private String title;
    /**
     * Request description.
     */
    private String description;
    /**
     * Request date.
     */
    private LocalDateTime date;
    /**
     * Request author.
     */
    private User author;
    /**
     * Request category.
     */
    private RequestCategory category;
    /**
     * Request status.
     */
    private RequestStatus status;
    /**
     * Request files list.
     */
    private List<File> files;
    /**
     * Request comments list.
     */
    private List<Comment> comments;

    /**
     * Default constructor.
     * @param id id.
     * @param title title.
     * @param description description.
     * @param date date.
     * @param author author.
     * @param category category.
     * @param status status.
     */
    public UserRequest(int id, String title, String description, LocalDateTime date, User author, RequestCategory category, RequestStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.author = author;
        this.category = category;
        this.status = status;
        this.comments = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    /**
     * Getter.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter.
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter.
     * @param title new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter.
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter.
     * @param description new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter.
     * @return create date.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Setter.
     * @param date new date.
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
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
     * @return category.
     */
    public RequestCategory getCategory() {
        return category;
    }

    /**
     * Setter.
     * @param category new category.
     */
    public void setCategory(RequestCategory category) {
        this.category = category;
    }

    /**
     * Getter.
     * @return status.
     */
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Setter.
     * @param status new status.
     */
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    /**
     * Returns list of request files.
     * @return ;ist of files.
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * Set list of files. Setter.
     * @param files new list of files.
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }

    /**
     * Returns list of request comments.
     * @return list of comments.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Setter.
     * @param comments new list of comments.
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Adds new comment to comments lists.
     * @param c new comment.
     */
    public void addComment(Comment c) {
        comments.add(c);
    }

    /**
     * Adds new file to files list.
     * @param f new file.
     */
    public void addFile(File f) {
        files.add(f);
    }

    /**
     * Default toString method.
     * @return string representation of request model.
     */
    @Override
    public String toString() {
        return "UserRequest{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", date=" + date
                + ", author=" + author
                + ", category=" + category
                + ", status=" + status
                + ", files=" + files
                + ", comments=" + comments
                + '}';
    }
}
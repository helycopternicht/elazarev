package ru.elazarev.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tracker item - one task.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 08.04.17
 */
public class TrackerItem {

    /**
     * Id of tracker item.
     */
    private String id;

    /**
     * Name of tracker item.
     */
    private String name;

    /**
     * Description of tracker item.
     */
    private String description;

    /**
     * Creation date of tracker item.
     */
    private long createdAt;

    /**
     * List of comments of tracker item.
     */
    private List<String> comments;

    /**
     * Constructor.
     */
    public TrackerItem() {
        this.createdAt = new Date().getTime();
        this.comments = new ArrayList<>();
    }

    /**
     * Constructor by another TrackerItem.
     * @param item - item to construct new item
     */
    public TrackerItem(TrackerItem item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.createdAt = item.getCreatedAt();
        this.comments = item.getComments();
    }

    /**
     * All fields constructor.
     * @param id - id of item
     * @param name - name of item
     * @param description - description of item
     * @param crAt - createdAt of item
     * @param comments - comments of item
     */
    public TrackerItem(String id, String name, String description, long crAt, List<String> comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = crAt;
        this.comments = comments;
    }

    /**
     * To string method.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("TrackerItem[id=%s, name=%s, description=%s, createdAt=%s]%s",
                getId(), getName(), getDescription(), getCreatedAt(), System.getProperty("line.separator")));

        if (this.comments.size() > 0) {
            sb.append(String.format("Comments:%s", System.getProperty("line.separator")));
            for (String msg : this.comments) {
                sb.append(String.format("    %s%s", msg,  System.getProperty("line.separator")));
            }
        }
        return sb.toString();
    }

    /**
     * Getter for id field.
     * @return - id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id field.
     * @param id - id of item
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for name item.
     * @return name of item
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     * @param name - name of item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for description field.
     * @return - description of item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description field.
     * @param description - new description of item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for createdAt field.
     * @return - created at of item
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter for createdAt field.
     * @param createdAt - new created at of item
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Getter for comments list.
     * @return - array of comments
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Setter for comments filed.
     * @param comments - new list of comments
     */
    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    /**
     * Method adds String message as comment in item.
     * @param msg - new comment
     */
    public void addComment(String msg) {
        this.comments.add(msg);
    }
}

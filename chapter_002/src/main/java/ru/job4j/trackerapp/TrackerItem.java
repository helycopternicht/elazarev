package ru.job4j.trackerapp;

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
     * @param id - id of tracker item
     * @param name - name of tracker item
     * @param description - description of tracker item
     */
    public TrackerItem(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = new Date().getTime();
        this.comments = new ArrayList<>();
    }

    /**
     * All field constructor.
     * @param id - id of tracker item
     * @param name - name of tracker item
     * @param description - description of tracker item
     * @param createdAt - create date
     * @param comments - List of comments
     */
    public TrackerItem(String id, String name, String description, long createdAt, List<String> comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.comments = comments;
    }

    /**
     * To string method.
     * @return
     */
    @Override
    public String toString() {
        return "Item ID:" + this.getId() + " Item name: " + this.getId() + " Item description: " + this.getDescription();
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

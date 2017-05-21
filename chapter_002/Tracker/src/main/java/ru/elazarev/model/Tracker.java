package ru.elazarev.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tracker class for store tracker items.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 08.04.17
 */
public class Tracker {

    /**
     * Internal storage for tracker items.
     */
    private List<TrackerItem> items;

    /**
     * Object to generate unique id.
     */
    private IdGenerator generator;

    /**
     * Default constructor.
     */
    public Tracker() {
        this.items = new ArrayList<>();
        this.generator = new IdGenerator();
    }

    /**
     * Method to add items in tracker.
     * @param item - Tracker item to add
     * @return - added Tracker item
     */
    public TrackerItem add(TrackerItem item) {
        if (item.getId() == null) {
            item.setId(String.valueOf(this.generator.generate()));
        }

        this.items.add(item);
        return item;
    }

    /**
     * Method to update tracker item in tracker.
     * Replace item with same id by @param item
     * @param item item to update
     */
    public void update(TrackerItem item) {
        int index = indexOf(item);
        if (index > -1) {
            this.items.set(index, item);
        }
    }

    /**
     * Returns all items in tracker.
     * @return TrackerItem[] - list of all items.
     */
    public TrackerItem[] findAll() {
        return items.toArray(new TrackerItem[this.items.size()]);
    }

    /**
     * Delete item if it exist in tracker.
     * @param item - item to remove
     */
    public void delete(TrackerItem item) {
        this.items.remove(item);
    }

    /**
     * Returns item by its name if it exists.
     * @param name - String name of item
     * @return Item or null
     */
    public TrackerItem findByName(String name) {
        TrackerItem result = null;
        for (TrackerItem item : this.items) {
            if (name.equals(item.getName())) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Returns item by its id if it exists.
     * @param id - String id of item
     * @return Item or null
     */
    public TrackerItem findById(String id) {
        TrackerItem result = null;
        for (TrackerItem item : this.items) {
            if (id.equals(item.getId())) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Returns a new list containing the filtered elements by substring.
     * @param substr - substring to filter tracker items
     * @return - new filtered list of items
     */
    public List<TrackerItem> filterByName(String substr) {
        List<TrackerItem> list = new ArrayList<>();
        for (TrackerItem item : this.items) {
            if (item.getName().startsWith(substr)) {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Method to find index of the item.
     * @param item - tracker item to find its index
     * @return int index of item or -1 of not exists
     */
    private int indexOf(TrackerItem item) {
        return this.items.indexOf(item);
    }
}

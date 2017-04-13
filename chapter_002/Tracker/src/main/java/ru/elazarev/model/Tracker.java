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
    private TrackerItem[] items;

    /**
     * Count of elements in storage.
     */
    private int size;

    /**
     * Object to generate unique id.
     */
    private IdGenerator generator;

    /**
     * Const for default length of the items storage.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Const for default increase rate of internal storage.
     */
    private static final int MAGNIFICATION_FACTOR = 2;

    /**
     * Default constructor.
     */
    public Tracker() {
        this.items = new TrackerItem[DEFAULT_SIZE];
        this.size = 0;
        this.generator = new IdGenerator();
    }

    /**
     * Method to add items in tracker.
     * @param item - Tracker item to add
     * @return - added Tracker item
     */
    public TrackerItem add(TrackerItem item) {
        checkCapacity();

        if (item.getId() == null) {
            item.setId(String.valueOf(this.generator.generate()));
        }

        this.items[size++] = item;
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
            this.items[index] = item;
        }
    }

    /**
     * Returns all items in tracker.
     * @return TrackerItem[] - list of all items.
     */
    public TrackerItem[] findAll() {
        TrackerItem[] result = new TrackerItem[this.size];
        System.arraycopy(this.items, 0, result, 0, this.size);
        return result;
    }

    /**
     * Delete item if it exist in tracker.
     * @param item - item to remove
     */
    public void delete(TrackerItem item) {
        int index = indexOf(item);
        if (index > -1) {
            System.arraycopy(this.items, index + 1, this.items, index, this.size - index);
            this.size--;
        }
    }

    /**
     * Returns item by its name if it exists.
     * @param name - String name of item
     * @return Item or null
     */
    public TrackerItem findByName(String name) {
        TrackerItem result = null;
        for (int i = 0; i < this.size; i++) {
            if (name.equals(this.items[i].getName())) {
                result = this.items[i];
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
        for (int i = 0; i < this.size; i++) {
            if (id.equals(this.items[i].getId())) {
                result = this.items[i];
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
        for (int i = 0; i < this.size; i++) {
            if (items[i].getName().startsWith(substr)) {
                list.add(items[i]);
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
        int index = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Checks tracker needs to increase the storage and
     * increase if it needed.
     */
    private void checkCapacity() {
        if (items.length < this.size + 1) {
            increaseStorage();
        }
    }

    /**
     * Method to increase internal storage use MAGNIFICATION_FACTOR const.
     */
    private void increaseStorage() {
        TrackerItem[] newStorage = new TrackerItem[this.items.length * MAGNIFICATION_FACTOR];
        System.arraycopy(this.items, 0, newStorage, 0, this.items.length);
        this.items = newStorage;
    }
}

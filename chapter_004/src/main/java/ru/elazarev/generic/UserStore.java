package ru.elazarev.generic;

/**
 * Store to contain User based elements.
 * @param <T> - type of elements to contain
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
public class UserStore<T extends User> implements Store<T> {

    /**
     * Internal storage of user based elements.
     */
    private SimpleArray<T> storage;

    /**
     * Default constructor.
     */
    public UserStore() {
        this.storage = new SimpleArray<T>();
    }

    /**
     * Adds el to store.
     * @param el - element to edd.
     */
    @Override
    public void add(T el) {
        this.storage.add(el);
    }

    /**
     * Update oldVal to newVal in store.
     * @param oldVal - element to update.
     * @param newVal - update element.
     * @return
     */
    @Override
    public boolean update(T oldVal, T newVal) {
        return this.storage.update(oldVal, newVal);
    }

    /**
     * Deletes element el from store.
     * @param el - element to delete
     * @return true if success false else.
     */
    @Override
    public boolean delete(T el) {
        return this.storage.delete(el);
    }
}

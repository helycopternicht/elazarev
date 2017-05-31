package ru.elazarev.generic;

/**
 * Store to contain Role based elements.
 * @param <T> - type to contain
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 29.05.17
 */
public class RoleStore<T extends Role> implements Store<T> {
    /**
     * Internal storage of Role based elements.
     */
    private SimpleArray<T> storage;

    /**
     * Default constructor.
     */
    public RoleStore() {
        this.storage = new SimpleArray<T>();
    }

    /**
     * Adds element el in store.
     * @param el - element to add.
     */
    @Override
    public void add(T el) {
        this.storage.add(el);
    }

    /**
     * Update oldVal to newVal in store.
     * @param oldVal - element to update.
     * @param newVal - update element.
     * @return true if success false else.
     */
    @Override
    public boolean update(T oldVal, T newVal) {
        return this.storage.update(oldVal, newVal);
    }

    /**
     * Deletes element el from store.
     * @param el - element to delete
     * @return true if success and false else.
     */
    @Override
    public boolean delete(T el) {
        return this.storage.delete(el);
    }
}

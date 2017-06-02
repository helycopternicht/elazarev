package ru.elazarev.generic;

/**
 * Abstract class store.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @param <T> type of elements to contain
 * @since 02.06.17
 */
abstract class AbstractStore<T extends Base>  implements Store<T> {
    /**
     * Internal storage of user based elements.
     */
    private SimpleArray<T> storage;

    /**
     * Default constructor.
     * @param storage inner storage
     */
    AbstractStore(SimpleArray<T> storage) {
        this.storage = storage;
    }

    /**
     * Adds el to store.
     * @param el element to edd.
     */
    @Override
    public void add(T el) {
        this.storage.add(el);
    }

    /**
     * Update oldVal to newVal in store.
     * @param oldVal element to update.
     * @param newVal update element.
     * @return
     */
    @Override
    public boolean update(T oldVal, T newVal) {
        return this.storage.update(oldVal, newVal);
    }

    /**
     * Deletes element el from store.
     * @param el element to delete
     * @return true if success false else.
     */
    @Override
    public boolean delete(T el) {
        return this.storage.delete(el);
    }

    /**
     * Getter for storage field.
     * @return storage field
     */
    public SimpleArray<T> getStorage() {
        return storage;
    }

    /**
     * Storage setter.
     * @param storage new storage
     */
    public void setStorage(SimpleArray<T> storage) {
        this.storage = storage;
    }
}

package ru.elazarev.generic;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
abstract class AbstractStore<T extends Base>  implements Store<T> {
    /**
     * Internal storage of user based elements.
     */
    protected SimpleArray<T> storage;

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

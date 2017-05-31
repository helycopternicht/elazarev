package ru.elazarev.generic;

/**
 * Store interface.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @param <T> - type of elements to contain
 * @since 29.05.17
 */
public interface Store<T extends Base> {
    /**
     * Method to add element in store.
     * @param el - element to add
     */
    void add(T el);

    /**
     * Method to update oldVal to newVal in store.
     * @param oldVal - element to update.
     * @param newVal - update element.
     * @return true if success
     */
    boolean update(T oldVal, T newVal);

    /**
     * Method delete element el from store.
     * @param el - element to delete
     * @return true or false.
     */
    boolean delete(T el);
}

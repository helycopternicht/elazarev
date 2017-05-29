package ru.elazarev.generic;

/**
 * Simple collection based on array.
 * @param <T> type of value contains to.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 26.05.17
 */
public class SimpleArray<T> {
    /**
     * Internal storage for elements.
     */
    private T[] storage;

    /**
     * Index for next element.
     */
    private int index;

    /**
     * Internal storage magnification factor.
     */
    private double coefficient = 2;

    /**
     * Constructor.
     * @param initialCapacity - internal storage initial length.
     */
    public SimpleArray(int initialCapacity) {
        this.storage = (T[]) new Object[initialCapacity];
    }

    /**
     * Default constructor with default initial capacity 10.
     */
    public SimpleArray() {
        this(10);
    }

    /**
     * Adds element el in collection.
     * @param el - element to add.
     */
    public void add(T el) {
        overflowCheck();
        this.storage[index++] = el;
    }

    /**
     * Setts element at index specified by param indexToUpdate to value specified by param newValue.
     * @param indexToUpdate - index of element to update.
     * @param newValue - new value of element
     * @throws IndexOutOfBoundsException if indexToUpdate is incorrect
     * @return - old value
     */
    public T update(int indexToUpdate, T newValue) {
        if (indexToUpdate >= this.index) {
            throw new IndexOutOfBoundsException("Have not element at index " + indexToUpdate);
        }

        T oldValue = this.storage[indexToUpdate];
        this.storage[indexToUpdate] = newValue;
        return oldValue;
    }

    /**
     * Delete toDelete element in collection if it exists.
     * @param toDelete - value of element
     * @return true if element is deleted and false else
     */
    public boolean delete(T toDelete) {
        for (int i = 0; i < index; i++) {
            if (this.storage[i].equals(toDelete)) {
                System.arraycopy(this.storage, i + 1, this.storage, i, this.storage.length - this.index);
                this.storage[--this.index] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns element at index specified by param index.
     * @param index - index to find element
     * @throws IndexOutOfBoundsException if index is incorrect.
     * @return - value of element
     */
    public T get(int index) {
        if (index >= this.index) {
            throw new IndexOutOfBoundsException("Have not element at index " + index);
        }
        return this.storage[index];
    }

    /**
     * Increases internal storage if it needed.
     */
    private void overflowCheck() {
        if (this.index + 1 == this.storage.length) {
            int storageLength = this.storage.length;
            T[] newStorage = (T[]) new Object[(int) (storageLength * this.coefficient)];
            System.arraycopy(storage, 0, newStorage, 0, storageLength);
            this.storage = newStorage;
        }
    }
}

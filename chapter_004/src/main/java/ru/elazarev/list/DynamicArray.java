package ru.elazarev.list;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Data structure based on array.
 * @param <T> type of elements to contain
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 31.05.17
 */
public class DynamicArray<T> implements List<T> {

    /**
     * Inner array to store elements.
     */
    private T[] storage;

    /**
     * Count of elements in storage.
     */
    private int size;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Factor to grow inner storage.
     */
    private static final int GROW_FACTOR = 2;

    /**
     * Constructor with initial capacity.
     * @param initialCapacity - length of inner array
     */
    public DynamicArray(int initialCapacity) {
        this.storage = (T[]) new Object[initialCapacity];
    }

    /**
     * Default constructor.
     */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    }

    /**
     * Returns count of elements in list.
     * @return - count of elements
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if has elements.
     * @return true or false
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns true is param o contains in list.
     * @param o - object to check
     * @return true or false
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Returns list iterator.
     * @return Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new DynArrIterator();
    }

    /**
     * Returns array with all list elements.
     * @return array
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.storage, this.size);
    }

    /**
     * Unsupported.
     * @param a - 1
     * @param <T1> - type
     * @return - array
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    /**
     * Add element t in list.
     * @param t - element to add
     * @return true
     */
    @Override
    public boolean add(T t) {
        checkCapacity();
        this.storage[size++] = t;
        return true;
    }

    /**
     * Add element in list at specified index.
     * @param index - index where to add
     * @param element - element to add
     */
    @Override
    public void add(int index, T element) {
        checkCapacity();
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(this.storage, index, this.storage, index + 1, this.size - index);
        this.storage[index] = element;
        size++;
    }

    /**
     * Removes element from list.
     * @param o - object to remove.
     * @return true if success
     */
    @Override
    public boolean remove(Object o) {
        int index = this.indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    /**
     * Removes element at specified index from list.
     * @param index - index from where remove element.
     * @return value of removed element
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        T oldVal = this.storage[index];

        System.arraycopy(this.storage, index + 1, this.storage, index, this.storage.length - this.size);
        this.storage[--this.size] = null;

        return oldVal;
    }

    /**
     * Returns true if this list contains all of the elements of the
     * specified collection.
     * @param c - collections of elements to test.
     * @return true of false
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        for (Object o : c) {
            if (this.indexOf(o) == -1) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list.
     * @param c - collection to append
     * @return true
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T o : c) {
            this.add(o);
        }
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position.
     * @param index - position to add
     * @param c - collection with elements to add.
     * @return true
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        for (T o : c) {
            this.add(index++, o);
        }
        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     * @param c - collection with elements to remove
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object o : c) {
            if (this.remove(o)) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Removes from this list all of its elements that are not contained in the
     * specified collection.
     * @param c - collection
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        for (int i = 0; i < this.size;) {
            if (!c.contains(this.storage[i])) {
                remove(i);
                res = true;
                continue;
            }
            i++;
        }
        return res;
    }

    /**
     * Removes all elements from this list.
     */
    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.storage[i] = null;
        }
        this.size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - position
     * @return element value
     */
    @Override
    public T get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return this.storage[index];
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index position
     * @param element element to replace.
     * @return the element previously at the specified position
     */
    @Override
    public T set(int index, T element) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        T oldVal = this.storage[index];
        this.storage[index] = element;
        return oldVal;
    }

    /**
     * Returns index of element o in this list.
     * @param o object to find
     * @return index or -1 of element not contains in this list
     */
    @Override
    public int indexOf(Object o) {
        int result = -1;
        for (int i = 0; i < this.size; i++) {
            if (o.equals(this.storage[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Returns last index of element o in this list.
     * @param o object to find
     * @return index or -1 of element not contains in this list
     */
    @Override
    public int lastIndexOf(Object o) {
        int result = -1;
        for (int i = size; i >= 0; i--) {
            if (o.equals(this.storage[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Unsupported!!!
     * @return NOP
     */
    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported!!!
     * @return NOP
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported!!!
     * @return NOP
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method grow inner storage if it needed.
     */
    private void checkCapacity() {
        if (this.size == this.storage.length) {
            this.storage = Arrays.copyOf(this.storage, this.storage.length * GROW_FACTOR);
        }
    }

    /**
     * DynamicArray iterator.
     */
    class DynArrIterator implements Iterator<T> {
        /**
         * Cursor position.
         */
        private int cursor;

        /**
         * Default constructor.
         */
        DynArrIterator() {
        }

        /**
         * Returns true if cursor have not reach end of the list.
         * @return true or false
         */
        @Override
        public boolean hasNext() {
            return this.cursor < size;
        }

        /**
         * Returns next element in list.
         * @return next element
         */
        @Override
        public T next() {
            if (cursor == size) {
                throw new NoSuchElementException();
            }
            return storage[cursor++];
        }
    }
}

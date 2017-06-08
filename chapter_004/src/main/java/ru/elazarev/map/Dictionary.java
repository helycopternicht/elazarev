package ru.elazarev.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple map implementation.
 * @param <K> type of keys
 * @param <V> type of values
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 07.06.17
 */
public class Dictionary<K, V> implements Iterable<Dictionary.Entry<K, V>> {
    /**
     * Load factor.
     */
    private static final float LOAD_FACTOR = 0.7f;
    /**
     * Size of internal storage.
     */
    private static final int DEFAULT_BUCKET_SIZE = 16;
    /**
     * Growing factor of internal storage.
     */
    private static final int GROWING_FACTOR = 2;

    /**
     * Internal storage for map entry.
     */
    private Entry<K, V>[] buckets;

    /**
     * Count of elements in map.
     */
    private int size;

    /**
     * Element that contain key and value and reference on next element in bucket.
     * @param <K> type of keys
     * @param <V> type of values
     */
    static class Entry<K, V> {
        /**
         * Entry key.
         */
        private K key;
        /**
         * Entry value.
         */
        private V value;
        /**
         * Ref to next.
         */
        private Entry<K, V> next;

        /**
         * Default constructor.
         * @param key entry key
         * @param value entry value
         * @param next ref to next entry
         */
        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Setter for key field.
         * @return key of entry
         */
        public K getKey() {
            return key;
        }

        /**
         * Getter for value field.
         * @return entry value
         */
        public V getValue() {
            return value;
        }

        /**
         * Default toString.
         * @return string representation of entry
         */
        @Override
        public String toString() {
            return getClass().getName() + "@{" + "key = " + getKey() + " value = " + getValue() + "}";
        }
    }

    /**
     * Default constructor.
     */
    public Dictionary() {
        buckets = new Entry[DEFAULT_BUCKET_SIZE];
    }

    /**
     * Return count of elements in map.
     * @return count of entry.
     */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return true if map changed in after call this method and false else
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int index = getIndexForKey(key);
        if (buckets[index] == null) {
            buckets[index] = new Entry<>(key, value, null);
            result = true;
            size++;
        } else {
            Entry<K, V> last = buckets[index];
            while (last != null) {
                if (key.equals(key)) {
                    result = true;
                    last.value = value;
                    break;
                }
            }

            if (!result) {
                buckets[index] = new Entry<>(key, value, buckets[index]);
                size++;
            }
        }
        if (needToGrow()) {
            resize();
        }
        return result;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return elements value or null
     */
    public V get(K key) {
        int index = getIndexForKey(key);
        if (buckets[index] == null) {
            return null;
        }

        Entry<K, V> entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map.
     * @return true if key was in map and false else
     */
    public boolean delete(K key) {
        int index = getIndexForKey(key);
        if (buckets[index] == null) {
            return false;
        }

        Entry<K, V> prev = null;
        Entry<K, V> entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = null;
                    size--;
                    return true;
                } else {
                    prev.next = entry.next;
                    size--;
                    return true;
                }
            }
            prev = entry;
            entry = entry.next;
        }
        return false;
    }

    /**
     * Iterator.
     * @return Iterator
     */
    public Iterator<Entry<K, V>> iterator() {
        return new DictionaryIterator();
    }

    /**
     * Returns index of bucket where should place entry with specified key.
     * @param key key to get index
     * @return index for bucket storage
     */
    private int getIndexForKey(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() & (buckets.length - 1);
    }

    /**
     * Returns true if internal storage should grow count of buckets.
     * @return true or false
     */
    private boolean needToGrow() {
        if (size / buckets.length > LOAD_FACTOR) {
            return true;
        }
        return false;
    }

    /**
     * Grow bucket storage and replace all elements in map.
     */
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[buckets.length * GROWING_FACTOR];
        size = 0;

        for (int i = 0; i < oldBuckets.length; i++) {

            if (oldBuckets[i] == null) {
                continue;
            }

            Entry<K, V> entry = oldBuckets[i];
            while (entry != null) {
                insert(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    /**
     * Iterator class for Dictionary class.
     */
    class DictionaryIterator implements Iterator<Dictionary.Entry<K, V>> {

        /**
         * Count of elements that returned by this iterator.
         */
        private int countOut;
        /**
         * Index to current bucket.
         */
        private int cursor;
        /**
         * Entry to return next time.
         */
        private Entry<K, V> last;

        /**
         * Returns true if iterator has more elements.
         * @return true of false
         */
        @Override
        public boolean hasNext() {
            return size > countOut;
        }

        /**
         * Returns next element in iterator.
         * @throws NoSuchElementException if there has no more elements
         * @return Entry or null
         */
        @Override
        public Entry<K, V> next() {
            if (countOut >= size) {
                throw new NoSuchElementException();
            }

            for (; cursor < buckets.length && last == null; cursor++) {
                if (buckets[cursor] == null) {
                    continue;
                }
                last = buckets[cursor];
            }

            Entry<K, V> result = last;
            last = last.next;
            countOut++;
            return result;
        }
    }
}

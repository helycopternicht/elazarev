package ru.elazarev.extra;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Extra task class.
 * @param <E> type of elements to work with.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 03.07.17
 */
public class ExtraTask<E> {

    /**
     * Method make one sorted array from two arrays.
     * @param sorted sorted array.
     * @param unsorted unsorted array
     * @param comparator comparator implementation to compare elements in arrays.
     * @throws IllegalArgumentException if any from arguments is null.
     * @return new sorted array that contain all elements from sorted and unsorted arrays.
     */
    public E[] merge(E[] sorted, E[] unsorted, Comparator<E> comparator) {

        if (sorted == null || unsorted == null || comparator == null) {
            throw new IllegalArgumentException();
        }

        E[] result = (E[]) new Object[sorted.length + unsorted.length];
        E[] newSorted = unsorted.clone();
        Arrays.sort(newSorted);

        int r = 0;
        int f = 0;
        int s = 0;

        while (r < result.length) {
            if (f == sorted.length) {
                result[r++] = newSorted[s++];
            } else if (s == newSorted.length) {
                result[r++] = sorted[f++];
            } else {

                result[r++] = comparator.compare(sorted[f], newSorted[s]) > 0 ? newSorted[s++] : sorted[f++];
            }
        }
        return result;
    }
}

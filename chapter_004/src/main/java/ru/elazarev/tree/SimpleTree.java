package ru.elazarev.tree;

/**
 * Simple tree interface.
 * @param <E> type of elements to contain.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 12.06.17
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return true or false
     */
    boolean add(E parent, E child);
}
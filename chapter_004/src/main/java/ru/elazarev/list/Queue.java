package ru.elazarev.list;

/**
 * Queue data-structure implementation.
 * @param <E> type of stored elements
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
public class Queue<E> {
    /**
     * Inner storage.
     */
    private DoubleLinkedList<E> list;

    /**
     * Default constructor.
     */
    public Queue() {
        list = new DoubleLinkedList<>();
    }

    /**
     * Added element to end of queue.
     * @param e element to add
     */
    public void push(E e) {
        list.add(e);
    }

    /**
     * Removes element from begin of queue.
     * @return removed elements value
     */
    public E pop() {
        return list.remove(0);
    }
}

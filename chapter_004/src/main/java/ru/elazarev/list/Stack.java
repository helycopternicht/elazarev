package ru.elazarev.list;

/**
 * Implementation of Stack data-structure.
 * @param <E> type of elements to store.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
public class Stack<E> {
    /**
     * Inner storage.
     */
    private DoubleLinkedList<E> list;

    /**
     * Default constructor.
     */
    public Stack() {
        list = new DoubleLinkedList<>();
    }

    /**
     * Added element e to end of stack.
     * @param e element to add
     */
    public void push(E e) {
        list.add(e);
    }

    /**
     * Removes and return last added element.
     * @return last added element
     */
    public E pop() {
        return list.remove(list.size() - 1);
    }
}

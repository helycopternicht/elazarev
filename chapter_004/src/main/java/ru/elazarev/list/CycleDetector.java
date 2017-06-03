package ru.elazarev.list;

/**
 * Class to detect cycles in linked list.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
public class CycleDetector {

    /**
     * Returns true if linked list head has cycles.
     * @param head linked list to check
     * @param <T> type of elements in linked list
     * @return true or false
     */
    public static <T> boolean hasCycle(Node<T> head) {
        if (head == null) {
            return false;
        }

        int i = 0;
        Node<T> node = head;
        while (node.getNext() != null) {
            if (hasCycle(i, head, node.getNext())) {
                return true;
            }
            node = node.getNext();
            i++;
        }
        return false;
    }

    /**
     * Checks that node is in range from 0 to index.
     * @param index end of range to check
     * @param head linked list of nodes
     * @param node node to find
     * @param <T> type of elements in linked list
     * @return true if node in range and false else
     */
    private static <T> boolean hasCycle(int index, Node<T> head, Node<T> node) {
        int i = 0;
        while (head != null && i < index) {
            if (node == head) {
                return true;
            }
            head = head.getNext();
            i++;
        }
        return false;
    }
}

/**
 * Linked list node.
 * @param <T> type of elements to contain
 */
class Node<T> {
    /**
     * Value of node.
     */
    private T value;
    /**
     * Ref to next node in list.
     */
    private Node<T> next;

    /**
     * Default constructor.
     * @param value value of new node
     */
    Node(T value) {
        this.value = value;
    }

    /**
     * Getter to value field.
     * @return value field
     */
    public T getValue() {
        return value;
    }

    /**
     * Setter to value field.
     * @param value new value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Getter for next field.
     * @return next field
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Setter for next field.
     * @param next new next field
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}

package ru.elazarev.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple search tree class.
 * @param <E> type of elements to contain
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 16.06.17
 */
public class SearchTree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Root of the tree.
     */
    private Node<E> root;

    /**
     * Node class to store elements of tree.
     * @param <E> type of elements to store
     */
    static class Node<E> {
        /**
         * Ref to parent node.
         */
        private Node<E> parent;
        /**
         * Ref to left child.
         */
        private Node<E> left;
        /**
         * Ref to right child.
         */
        private Node<E> right;
        /**
         * Value of node.
         */
        private E value;

        /**
         * Default constructor.
         * @param val - value of node
         */
        private Node(E val) {
            this.value = val;
        }

        /**
         * Constructro with value and parent.
         * @param val value of node
         * @param parent parent of node
         */
        private Node(E val, Node<E> parent) {
            this.value = val;
            this.parent = parent;
        }

        /**
         * Getter for left field.
         * @return left value
         */
        private Node<E> getLeft() {
            return left;
        }

        /**
         * Setter for left field.
         * @param left - new left
         */
        private void setLeft(Node<E> left) {
            this.left = left;
        }

        /**
         * Getter for right field.
         * @return right field
         */
        private Node<E> getRight() {
            return right;
        }

        /**
         * Setter for right field.
         * @param right new right field
         */
        private void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * Getter for value field.
         * @return value of node
         */
        public E getValue() {
            return value;
        }
    }

    /**
     * Default constructor.
     */
    public SearchTree() {
    }

    /**
     * Unsupported operation.
     * @param parent parent.
     * @param child child.
     * @return NOP
     */
    @Override
    public boolean add(E parent, E child) {
        throw new UnsupportedOperationException();
    }

    /**
     * Add element e in tree.
     * @param e element to add
     * @return true if element added and false else.
     */
    public boolean add(E e) {
        if (root == null) {
            root = new Node<>(e);
            return true;
        }

        Node<E> prev = null;
        Node<E> node = root;
        while (node != null) {
            if (e.compareTo(node.getValue()) == 0) {
                return false;
            } else if (e.compareTo(node.getValue()) < 0) {
                prev = node;
                node = node.getLeft();
            } else {
                prev = node;
                node = node.getRight();
            }
        }

        Node<E> newNode = new Node<>(e, prev);
        if (e.compareTo(prev.getValue()) < 0) {
            prev.setLeft(newNode);
        } else {
            prev.setRight(newNode);
        }
        return true;
    }

    /**
     * Iterator of elements.
     * @return treeiterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator(root);
    }

    /**
     * Iterator for search tree.
     * @param <E> type of elements
     */
    class TreeIterator<E> implements Iterator<E> {

        /**
         * Next node to return.
         */
        private Node<E> next;

        /**
         * Default constructor.
         * @param r - root node of tree.
         */
        TreeIterator(Node<E> r) {
            Node<E> n = r;
            while (n.left != null) {
                n = n.left;
            }
            this.next = n;
        }

        /**
         * Returns true if in tree have more elements.
         * @return true or false.
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Returns next element in tree.
         * @return next element
         */
        @Override
        public E next() {
            Node<E> e = next;
            if (e == null) {
                throw new NoSuchElementException();
            }

            next = getNextNode(e);
            return e.getValue();
        }

        /**
         * Defines next element in tree.
         * @param t current node
         * @return next element of tree
         */
        private Node<E> getNextNode(Node<E> t) {
            if (t == null) {
                return null;
            } else if (t.right != null) {
                Node<E> p = t.right;
                while (p.left != null) {
                    p = p.left;
                }
                return p;
            } else {
                Node<E> p = t.parent;
                Node<E> ch = t;
                while (p != null && ch == p.right) {
                    ch = p;
                    p = p.parent;
                }
                return p;
            }
        }
    }
}

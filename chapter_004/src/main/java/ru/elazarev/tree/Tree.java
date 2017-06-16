package ru.elazarev.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * Simple tree with many children.
 * @param <E> type of elements to contain
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 12.06.17
 */
class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Root of tree.
     */
    private Node<E> root;

    /**
     * Tree entry class.
     * @param <E> type of value
     */
    static class Node<E> {
        /**
         * List of node children.
         */
        private List<Node<E>> children;
        /**
         * Entry value.
         */
        private E value;

        /**
         * Default constructor.
         * @param value value of node.
         */
        Node(E value) {
            this.value = value;
            children = new ArrayList<>();
        }

        /**
         * Method adds element into tree.
         * @param e value of added element.
         */
        public void addChild(E e) {
            children.add(new Node<>(e));
        }

        /**
         *
         * Returns all children of noew.
         * @return list of children
         */
        public List<Node<E>> getChildren() {
            return children;
        }

        /**
         * Returns value of node.
         * @return value of node.
         */
        public E getValue() {
            return value;
        }
    }

    /**
     * Method adds element child to parent.
     * @param parent parent of added child.
     * @param child element to add.
     * @return true if element added and false else.
     */
    @Override
    public boolean add(E parent, E child) {
        if (root == null) {
            root = new Node<>(parent);
            root.addChild(child);
            return true;
        }

        if (root.value.equals(parent)) {
            root.addChild(child);
            return true;
        }

        Node<E> parNode = find(root.getChildren(), parent);
        if (parNode != null) {
            parNode.addChild(child);
            return true;
        }

        return false;
    }

    /**
     * Method find parent in nodes and its children.
     * @param nodes list of nodes
     * @param parent element to find.
     * @return null of Node
     */
    private Node<E> find(List<Node<E>> nodes, E parent) {
        for (Node<E> ch : nodes) {
            if (ch.value.compareTo(parent) == 0) {
                return ch;
            }
            return find(ch.getChildren(), parent);
        }
        return null;
    }

    /**
     * Default iterator.
     * @return iterator.
     */
    @Override
    public Iterator iterator() {
        return new TreeIterator();
    }

    /**
     * Iterator of tree class. Have no idea how to implement iterator
     * without additional memory. Or ref to parent...
     */
    class TreeIterator implements Iterator<Node<E>> {
        /**
         * Additional data struct to contains list of nodes in pre order.
         */
        private Queue<Node<E>> queue;
        /**
         * Iterator of queue.
         */
        private Iterator<Node<E>> it;

        /**
         * Default constructor.
         */
        TreeIterator() {
            queue = new LinkedList<>();
            if (root != null) {
                queue.add(root);
                addChildren(root.getChildren());
                it = queue.iterator();
            }
        }

        /**
         * Method converts tree to queue.
         * @param children - list of nodes to convert.
         */
        private void addChildren(List<Node<E>> children) {
            if (children == null) {
                return;
            }
            for (Node<E> child : children) {
                queue.add(child);
                addChildren(child.getChildren());
            }
        }

        /**
         * Returns true if tree contain more nodes and false else.
         * @return true or false
         */
        @Override
        public boolean hasNext() {
            if (it == null) {
                return false;
            }
            return it.hasNext();
        }

        /**
         * Returns next node in tree.
         * @throws NoSuchElementException if there have no more nodes.
         * @return next node
         */
        @Override
        public Node<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return it.next();
        }
    }
}

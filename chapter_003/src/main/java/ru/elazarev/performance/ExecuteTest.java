package ru.elazarev.performance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Class to execute collection performance test.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 17.05.17
 */
public class ExecuteTest {

    /**
     * Amount for add test.
     */
    public static final int ADD_TEST_AMOUNT = 10_000_000;

    /**
     * Amount for delete test.
     */
    public static final int REMOVE_TEST_AMOUNT = 10_000;

    /**
     * Main method.
     * @param args - should be empty
     */
    public static void main(String[] args) {
        startTest();
    }

    /**
     * Collection performance test. Print results in console.
     */
    private static void startTest() {
        CollectionPerformanceTest test = new CollectionPerformanceTest();
        Collection<String> arrList = new ArrayList<>();
        long arrayListTime = test.add(arrList, "testString", ADD_TEST_AMOUNT);

        Collection<String> linkedList = new LinkedList<>();
        long linkedListTime = test.add(linkedList, "testString", ADD_TEST_AMOUNT);

        Collection<String> treeSet = new TreeSet<>();
        long treeSetTime = test.add(treeSet, "testString", ADD_TEST_AMOUNT);

        System.out.println("ArrayList ADD = " + arrayListTime);
        System.out.println("LinkedList ADD = " + linkedListTime);
        System.out.println("TreeSet ADD = " + treeSetTime);

        long arrayLDeleteTime = test.delete(arrList, REMOVE_TEST_AMOUNT);

        long linkedListDeleteTime = test.delete(linkedList, REMOVE_TEST_AMOUNT);

        long treeSetDeleteTime = test.delete(treeSet, REMOVE_TEST_AMOUNT);

        System.out.println("ArrayList REMOVE = " + arrayLDeleteTime);
        System.out.println("LinkedList REMOVE = " + linkedListDeleteTime);
        System.out.println("TreeSet REMOVE = " + treeSetDeleteTime);
    }
}

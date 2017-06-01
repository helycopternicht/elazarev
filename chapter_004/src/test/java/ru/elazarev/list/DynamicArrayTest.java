package ru.elazarev.list;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * DynamicArrays test.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 01.06.17
 */
public class DynamicArrayTest {

    /**
     * size() test.
     */
    @Test
    public void whenDynArrIsEmptyThenSizeIsZeroAndViceVersa() {
        List<String> dynArr = new DynamicArray<>();

        assertThat(dynArr.size(), is(0));
        dynArr.add("Test string");
        assertThat(dynArr.size(), is(1));
    }

    /**
     * isEmpty() test.
     */
    @Test
    public void whenDynArrIsEmptyThenIsEmptyIsTrueAndViceVersa() {
        List<String> dynArr = new DynamicArray<>();

        assertThat(dynArr.isEmpty(), is(true));
        dynArr.add("Test string");
        assertThat(dynArr.isEmpty(), is(false));
    }

    /**
     * contains() test.
     */
    @Test
    public void whenCollectionContainsElementThenContainsReturnsTrueAndViceVersa() {
        List<String> dynArr = new DynamicArray<>();

        String testStr = "Test string";

        assertThat(dynArr.contains(testStr), is(false));
        dynArr.add(testStr);
        assertThat(dynArr.contains(testStr), is(true));
    }

    /**
     * toArray() test.
     */
    @Test
    public void toArrayMethodReturnsArrayWithAllElementsOfCollection() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.add("test");
        dynArr.add("test");
        dynArr.add("test");

        String[] exp = new String[] {"test", "test", "test"};

        assertThat(dynArr.toArray(), is(exp));
    }

    /**
     * add() method test.
     */
    @Test
    public void whenAddElementToCollectionThenItContainsAllElements() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.add("test1");
        dynArr.add("test2");
        dynArr.add("test3");
        dynArr.add("test3");
        dynArr.add("test3");
        dynArr.add("test3");
        dynArr.add("test3");
        dynArr.add("test3");
        dynArr.add("test3");
        dynArr.add("test3");
        dynArr.add("test3");

        assertThat(dynArr.contains("test1"), is(true));
        assertThat(dynArr.contains("test2"), is(true));
        assertThat(dynArr.contains("test3"), is(true));
        assertThat(dynArr.contains("test4"), is(false));
    }

    /**
     * add with index test.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAtIndexThatCollectionInsertsElementToSpecifiedIndex() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.add("test1");
        dynArr.add("test2");
        dynArr.add("test3");

        dynArr.add(1, "TEST_ELEMENT");

        assertThat(dynArr.contains("TEST_ELEMENT"), is(true));
        assertThat(dynArr.get(1), is("TEST_ELEMENT"));

        dynArr.add(100, "EXCEPTION!");
    }

    /**
     * remove() test.
     */
    @Test
    public void whenRemoveElementFromCollectionThanCollectionNotContainElement() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.add("test1");
        dynArr.add("test2");
        dynArr.add("test3");

        assertThat(dynArr.remove("test1"), is(true));
        assertThat(dynArr.remove("NotContains"), is(false));
        assertThat(dynArr.size(), is(2));
    }

    /**
     * remove at index test.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveIndexFromCollectionThanCollectionNotContainElementAtThisIndex() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.add("test1");
        dynArr.add("test2");
        dynArr.add("test3");

        assertThat(dynArr.remove(0), is("test1"));
        assertThat(dynArr.remove(0), is("test2"));
        assertThat(dynArr.remove(0), is("test3"));
        dynArr.remove(0);
    }

    /**
     * containsAll() test.
     */
    @Test
    public void whenCollectionContainAllOfAnotherCollectionElementsThatContainsAllReturnsTrue() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.add("test1");
        dynArr.add("test2");
        dynArr.add("test3");

        assertThat(dynArr.containsAll(Arrays.asList("test1", "test2", "test3")), is(true));
        assertThat(dynArr.containsAll(Arrays.asList("test1", "test2", "test3", "test4")), is(false));
    }

    /**
     * addAll() test.
     */
    @Test
    public void whenAddAllThenCollectionContainsAllElements() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.add("1");

        List<String> addCollection = Arrays.asList("2", "3", "4");

        List<String> exp = Arrays.asList("1", "2", "3", "4");

        dynArr.addAll(addCollection);

        assertThat(dynArr.size(), is(4));
        assertThat(dynArr.containsAll(exp), is(true));
    }

    /**
     * addAll() at specified index test.
     */
    @Test
    public void whenAddAllIntoSpecifiedIndexThenCollectionContainsAllElementsBeginFromSpecifiedIndex() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.add("1");

        List<String> addCollection = Arrays.asList("2", "3", "4");

        List<String> exp = Arrays.asList("1", "2", "3", "4");

        dynArr.addAll(0, addCollection);

        assertThat(dynArr.get(0), is("2"));
        assertThat(dynArr.size(), is(4));
        assertThat(dynArr.containsAll(exp), is(true));
        assertThat(dynArr.get(3), is("1"));
    }

    /**
     * removeAll() test.
     */
    @Test
    public void removeAllRemovesAllElementsContainedInSpecifiedCollections() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.addAll(Arrays.asList("1", "2", "3", "4"));

        boolean result = dynArr.removeAll(Arrays.asList("2", "3", "4"));

        assertThat(result, is(true));
        assertThat(dynArr.size(), is(1));
        assertThat(dynArr.contains("1"), is(true));
        assertThat(dynArr.contains("2"), is(false));
        assertThat(dynArr.contains("3"), is(false));
        assertThat(dynArr.contains("4"), is(false));
    }

    /**
     * retainAll() test.
     */
    @Test
    public void retainAllRemovesAllElementsNotContainsInSpecifiedCollection() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));

        boolean result = dynArr.retainAll(Arrays.asList("1", "2", "3"));
        assertThat(result, is(true));
        assertThat(dynArr.size(), is(3));
        assertThat(dynArr.contains("1"), is(true));
        assertThat(dynArr.contains("2"), is(true));
        assertThat(dynArr.contains("3"), is(true));
        assertThat(dynArr.contains("4"), is(false));
        assertThat(dynArr.contains("5"), is(false));
        assertThat(dynArr.contains("6"), is(false));
    }

    /**
     * clear() test.
     */
    @Test
    public void clearMethodRemovesAllElementsFromCollection() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));

        dynArr.clear();

        assertThat(dynArr.size(), is(0));
    }

    /**
     * get() test.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getReturnsElementAtSpecifiedIndex() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));

        assertThat(dynArr.get(0), is("1"));
        assertThat(dynArr.get(5), is("6"));
        dynArr.get(6);
    }

    /**
     * set() test.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void setReturnsElementThatWasAtSpecifiedIndexBefore() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.addAll(Arrays.asList("1"));

        assertThat(dynArr.set(0, "999"), is("1"));
        assertThat(dynArr.get(0), is("999"));
        dynArr.get(1);
    }

    /**
     * indexOf() test.
     */
    @Test
    public void indexOfReturnsIndexOfElement() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.addAll(Arrays.asList("1", "1"));

        assertThat(dynArr.indexOf("1"), is(0));
        assertThat(dynArr.indexOf("2"), is(-1));
    }

    /**
     * lastIndexOf() test.
     */
    @Test
    public void lastIndexOfReturnsLastIndexOfElement() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.addAll(Arrays.asList("1", "1"));

        assertThat(dynArr.lastIndexOf("1"), is(1));
        assertThat(dynArr.indexOf("2"), is(-1));
    }

    /**
     * iterator() test.
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratorTest() {
        List<String> dynArr = new DynamicArray<>();
        dynArr.addAll(Arrays.asList("1", "2", "3"));

        Iterator<String> it = dynArr.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("1"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("2"));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("3"));

        assertThat(it.hasNext(), is(false));
        it.next();
    }
}
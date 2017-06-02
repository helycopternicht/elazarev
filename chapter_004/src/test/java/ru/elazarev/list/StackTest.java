package ru.elazarev.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Stack class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 02.06.17
 */
public class StackTest {
    /**
     * push() and pop() methods test.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void pushAddElementToStackAndPopRemovesLastAddedElement() {
        Stack<String> stack = new Stack<>();
        stack.push("first");
        stack.push("second");
        stack.push("third");

        assertThat(stack.pop(), is("third"));
        assertThat(stack.pop(), is("second"));
        assertThat(stack.pop(), is("first"));
        stack.pop();
    }

}
package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing Point class.
* @author Eugene Lazarev (mailto:helycopternicht@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class PointTest {
    /**
    * Test is method.
    */
    @Test
    public void whenXIsTwoAndYIsFourThenTrue() {
        Point point = new Point(2, 4);
        assertThat(point.is(1, 2), is(true));
    }

    /**
    * Test is method.
    */
    @Test
    public void whenXIsTwoAndYIsThreeThenFalse() {
        Point point = new Point(2, 3);
        assertThat(point.is(1, 2), is(false));
    }
}
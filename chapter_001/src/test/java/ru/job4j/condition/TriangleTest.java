package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * Test for Triangle  class.
 *
 * @author Eugene Lazarev
 * @since 27.03.2017
 */
 public class TriangleTest {
	/**
     * Test area method.
     */
	@Test
	public void whenAddThreePointsThenReturnArea() {

		final int two = 2;
		final int three = 3;

		Point a = new Point(1, three);
		Point b = new Point(two, two);
		Point c = new Point(three, two);

		Triangle triangle = new Triangle(a, b, c);

		final double checked = 0.5;

		double result = triangle.area();

		assertThat(result, is(closeTo(checked, 0.01)));
	}

	/**
     * Test area method with incorrect points.
     */
    @Test
    public final void whenWrongAreaThenReturnZero() {

        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        Point c = new Point(0, 0);

        Triangle triangle = new Triangle(a, b, c);
		assertThat(triangle.area(), is(0d));
	}
 }
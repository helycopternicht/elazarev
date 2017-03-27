package ru.job4j.condition;

/**
 * Triangle class.
 *
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 27.03.2017
 */
public class Triangle {
	/**
	 * A point.
	 */
	private Point a;

	/**
	 * B point.
	 */
	private Point b;

	/**
	 * C point.
	 */
	private Point c;

	/**
	 * Default onstructor for class.
	 * @param a1 is the first point of the triangle
	 * @param b1 is the second point of the triangle
	 * @param c1 is the third point of the triangle
	 */
	public Triangle(final Point a1, final Point b1, final Point c1) {
		this.a = a1;
		this.b = b1;
		this.c = c1;
	}

	/**
	 * Method returns area of a triangle.
	 * @return area of a triangle or 0 when point are incorrect
	 * @throws Exception - "It is impossible to construct a triangle with these vertices"
	 */
	public double area() {
		double sideAB = this.a.distanceTo(this.b);
		double sideAC = this.a.distanceTo(this.c);
		double sideBC = this.b.distanceTo(this.c);

		boolean incorrectPoints = sideAB >= sideAC + sideBC && sideAC >= sideAB + sideBC && sideBC >= sideAC + sideAB;

		if (incorrectPoints) {
			return 0.;
		}

		double properiter = (sideAB + sideAC + sideBC) / 2;

		return Math.sqrt(properiter * (properiter - sideAB) * (properiter - sideAC) * (properiter - sideBC));
	}
}
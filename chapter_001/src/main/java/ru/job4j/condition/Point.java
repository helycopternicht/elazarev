package ru.job4j.condition;

/**
* Class present point in 2d coodrinate system.
* @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
* @since 24.03.2017
*/
public class Point {
   /**
   * Value on horisontal line.
   */
   private int x;
   /**
   * Value on vertical line.
   */
   private int y;
  /**
   * Default constructor.
   * @param x - int Value on horisontal line
   * @param y - int Value on vertical line
   */
   public  Point(int x, int y) {
      this.x = x;
      this.y = y;
  }
  /**
  * Getter for x value.
  * @return value of x field
  */
  public int getX() {
      return this.x;
  }
 /**
  * Getter for y value.
  * @return value of y field
  */
  public int getY() {
     return this.y;
  }

  /**
  * Method determine is point on the function or not.
  * @param a integer
  * @param b integer
  * @return true is point on the function and false else
  */
  public boolean is(int a, int b) {
    return this.y == a * this.x + b;
  }

  /**
   * Method returns distance between two points.
   * @param point is passed a point
   * @return distans between two points
   */
  public double distanceTo(Point point) {
    return Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
  }
}
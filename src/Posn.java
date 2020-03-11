/**
 * To represent the class Posn that represent the position of the shape.
 */
public class Posn {

  private double x;
  private double y;

  /**
   * Constructor of the class Posn.
   *
   * @param x to represent the X coordinate of Posn
   * @param y to represent the Y coordinate of Posn
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor of the class Posn.
   *
   * @param other to represent the copy Posn
   */
  public Posn(Posn other) {
    this.x = other.x;
    this.y = other.y;
  }

  /**
   * to get the x position.
   *
   * @return x
   */
  public double getX() {
    return x;
  }

  /**
   * to get the y position.
   *
   * @return y
   */
  public double getY() {
    return y;
  }

  /**
   * to set the x to the value of the new input.
   *
   * @param x represent the new x value
   * @return the new position with the new x value
   */
  public Posn setX(double x) {
    Posn p = new Posn(0, 0);
    p.x = x;
    return p;
  }

  /**
   * In order to the change the position to the string format.
   *
   * @return the string format of the position
   */
  public String posToString() {
    return this.x + " " + this.y;
  }

  /**
   * to set the y to the value of the new y input.
   *
   * @param y represent the new y value
   * @return the new position with the new y value
   */
  public Posn setY(double y) {
    Posn p = new Posn(0, 0);
    p.y = y;
    return p;
  }
}
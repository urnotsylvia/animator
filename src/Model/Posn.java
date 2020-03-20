package Model;

/**
 * To represent the class Model.Posn that represent the position of the shape.
 */
public class Posn {

  private double x;
  private double y;

  /**
   * Constructor of the class Model.Posn.
   *
   * @param x to represent the X coordinate of Model.Posn
   * @param y to represent the Y coordinate of Model.Posn
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor of the class Model.Posn.
   *
   * @param other to represent the copy Model.Posn
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
   */
  public void setX(double x) {
    this.x = x;
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
   */
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Posn)) {
      return false;
    } else {
      return (this.x == ((Posn) other).x
          && this.y == ((Posn) other).y);
    }
  }

  @Override
  public int hashCode() {
    int result = 2;
    result += 3 * Double.hashCode(this.x);
    result += 5 * Double.hashCode(this.y);
    return result;
  }
}

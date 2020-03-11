/**
 * The KeyFrame take parts in the animation that represent the basic information of the shape.
 */
public class KeyFrame {

  private int time;
  private int x;
  private int y;
  private double w;
  private double h;
  private RGBColor color;

  /**
   * to represent the constructor of the keyFrame.
   *
   * @param time  the starting time
   * @param x     the x position of the shape
   * @param y     the y position of the shape
   * @param w     the width of the shape
   * @param h     the height of the shape
   * @param color the color of the shape
   */
  public KeyFrame(int time, int x, int y, int w, int h, RGBColor color) {
    this.time = time;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.color = color;
  }

  /**
   * return the rgb color of the keyFrame.
   *
   * @return RGBColor
   */
  public RGBColor getColor() {
    return color;
  }

  /**
   * return the x of the position of the keyFrame.
   *
   * @return x position
   */
  public double getX() {
    return x;
  }

  /**
   * return the y of the position of the keyFrame.
   *
   * @return y position
   */
  public double getY() {
    return y;
  }

  /**
   * return the w of the corresponding keyFrame based on the shape.
   *
   * @return w
   */
  public double getW() {
    return w;
  }

  /**
   * return the h of the corresponding keyFrame based on the shape.
   *
   * @return h
   */
  public double getH() {
    return h;
  }

  /**
   * return the time of this keyFrame.
   *
   * @return time
   */
  public int getTime() {
    return time;
  }

  /**
   * convert the keyFrame to a String.
   *
   * @return the keyFrame as a String
   */
  public String keyToString() {
    return time + " " + x + " " + y + " " + w + " " + h + " " + color.asString();
  }
}

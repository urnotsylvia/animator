package cs3500.animator.Model;

/**
 * The Model.KeyFrame take parts in the animation that represent the basic information of the shape at a
 * specific tick.
 */
public class KeyFrame {

  private int time;
  private int x;
  private int y;
  private int w;
  private int h;
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
    if (w < 0.0001 || h < 0.0001) {
      throw new IllegalArgumentException("the dimension cannot be negative");
    }

    this.time = time;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.color = color;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof KeyFrame)) {
      return false;
    } else {
      return (this.time == ((KeyFrame) other).time
          && this.w <= ((KeyFrame) other).w + 0.0001
          && this.w >= ((KeyFrame) other).w - 0.0001
          && this.w <= ((KeyFrame) other).w + 0.0001
          && this.w >= ((KeyFrame) other).w - 0.0001
          && this.color.equals(((KeyFrame) other).color)
          && this.y <= ((KeyFrame) other).y + 0.0001
          && this.y >= ((KeyFrame) other).y - 0.0001
          && this.h <= ((KeyFrame) other).h + 0.0001
          && this.h >= ((KeyFrame) other).h - 0.0001);
    }
  }

  @Override
  public int hashCode() {
    int result = 3;
    result += 2 * Integer.hashCode(this.time);
    result += 3 * Integer.hashCode(this.x);
    result += 5 * Integer.hashCode(this.y);
    result += 7 * Double.hashCode(this.w);
    result += 11 * Double.hashCode(this.h);
    result += 13 * this.color.hashCode();
    return result;
  }

  /**
   * return the rgb color of the keyFrame.
   *
   * @return Model.RGBColor
   */
  public RGBColor getColor() {
    return color;
  }

  /**
   * return the x of the position of the keyFrame.
   *
   * @return x position
   */
  public int getX() {
    return x;
  }

  /**
   * return the y of the position of the keyFrame.
   *
   * @return y position
   */
  public int getY() {
    return y;
  }

  /**
   * return the w of the corresponding keyFrame based on the shape.
   *
   * @return w
   */
  public int getW() {
    return w;
  }

  /**
   * return the h of the corresponding keyFrame based on the shape.
   *
   * @return h
   */
  public int getH() {
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

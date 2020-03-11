import java.util.List;

/**
 * A class of shape Rectangle in the animation.
 */
public class Rectangle extends AShape {

  /**
   * the constructor to construct the rectangle.
   *
   * @param name     the name of the Rectangle
   * @param w        the width of the Rectangle
   * @param h        the height of the Rectangle
   * @param color    the color of the Rectangle
   * @param pos      the position of the Rectangle
   * @param keyFrame the keyFrame of the Rectangle
   */
  public Rectangle(String name, double w, double h, RGBColor color, Posn pos,
      List<KeyFrame> keyFrame) {
    super(name, w, h, color, pos, keyFrame);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Rectangle)) {
      return false;
    } else {
      return (
          this.name.equals(((Rectangle) other).name)
              && this.w <= ((Rectangle) other).w + 0.0001
              && this.w >= ((Rectangle) other).w - 0.0001
              && this.pos.equals(((Rectangle) other).pos)
              && this.color.equals(((Rectangle) other).color)
              && this.keyFrames.equals(((Rectangle) other).keyFrames)
              && this.h <= ((Rectangle) other).h + 0.0001
              && this.h >= ((Rectangle) other).h - 0.0001);
    }
  }

  /**
   * To check if it is valid to create the rectangle.
   *
   * @param name     the name of the Rectangle
   * @param w        the width of the Rectangle
   * @param h        the height of the Rectangle
   * @param color    the color of the Rectangle
   * @param pos      the position of the Rectangle
   * @param keyFrame the keyFrame of the Rectangle
   * @return a valid Rectangle or IllegalArgumentException
   */
  public static Rectangle createRect(String name, double w, double h, RGBColor color, Posn pos,
      List<KeyFrame> keyFrame) {
    if (w <= 0.0001 || h <= 0.0001) {
      throw new IllegalArgumentException("Invalid height and width to create a valid rectangle");
    } else {
      return new Rectangle(name, w, h, color, pos, keyFrame);
    }
  }
}
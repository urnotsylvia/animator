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

  /**
   * To check if it is valid to create the oval.
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
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Invalid height and width to create a valid rectangle");
    } else {
      return new Rectangle(name, w, h, color, pos, keyFrame);
    }
  }
}
import java.util.ArrayList;
import java.util.List;

/**
 * A class of Shape oval in the animation.
 */
public class Oval extends AShape {

  /**
   * the constructor to construct the oval.
   *
   * @param name     the name of Oval
   * @param x        the x radius of the Oval
   * @param y        the y radius of Oval
   * @param color    the color of the Oval
   * @param pos      the position of the Oval
   * @param keyFrame the keyFrame of the Oval
   */
  public Oval(String name, double x, double y, RGBColor color, Posn pos, List<KeyFrame> keyFrame) {
    super(name, x, y, color, pos, keyFrame);
  }

  /**
   * To check if it is valid to create the oval.
   *
   * @param name     the name of Oval
   * @param x        the x radius of the Oval
   * @param y        the y radius of Oval
   * @param color    the color of the Oval
   * @param pos      the position of the Oval
   * @param keyFrame the keyFrame of the Oval
   * @return a valid Oval or IllegalArgumentException
   */
  public static Oval createOval(String name, double x, double y, RGBColor color, Posn pos,
      List<KeyFrame> keyFrame) {
    if (x <= 0 || y <= 0) {
      throw new IllegalArgumentException("Invalid radius to create the oval");
    } else {
      return new Oval(name, x, y, color, pos, keyFrame);
    }
  }

  @Override
  public IShape getShape() {
    return new Oval("", 0, 0, new RGBColor(0, 0, 0), new Posn(0, 0), new ArrayList());
  }
}


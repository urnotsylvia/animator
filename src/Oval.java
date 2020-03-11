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
   * @param w        the vertical radius of the Oval
   * @param h        the horizontal radius of Oval
   * @param color    the color of the Oval
   * @param pos      the position of the Oval
   * @param keyFrame the keyFrame of the Oval
   */
  public Oval(String name, double w, double h, RGBColor color, Posn pos, List<KeyFrame> keyFrame) {
    super(name, w, h, color, pos, keyFrame);
  }

  /**
   * To check if it is valid to create the oval.
   *
   * @param name     the name of Oval
   * @param w        the x radius of the Oval
   * @param h        the y radius of Oval
   * @param color    the color of the Oval
   * @param pos      the position of the Oval
   * @param keyFrame the keyFrame of the Oval
   * @return a valid Oval or IllegalArgumentException
   */
  public static Oval createOval(String name, double w, double h, RGBColor color, Posn pos,
      List<KeyFrame> keyFrame) {
    if (w <= 0.0001 || h <= 0.0001) {
      throw new IllegalArgumentException("Invalid radius to create the oval");
    } else {
      return new Oval(name, w, h, color, pos, keyFrame);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Oval)) {
      return false;
    } else {
      return (this.name.equals(((Oval) other).name)
          && this.w <= ((Oval) other).w + 0.0001
          && this.w >= ((Oval) other).w - 0.0001
          && this.pos.equals(((Oval) other).pos)
          && this.color.equals(((Oval) other).color)
          && this.keyFrames.equals(((Oval) other).keyFrames)
          && this.h <= ((Oval) other).h + 0.0001
          && this.h >= ((Oval) other).h - 0.0001);
    }
  }

  @Override
  public IShape getShape() {
    return new Oval("", 0.0001, 0.0001, new RGBColor(0, 0, 0), new Posn(0, 0), new ArrayList());
  }
}


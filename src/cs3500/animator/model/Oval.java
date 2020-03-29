package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an oval in the animation.
 */
public class Oval extends AShape {

  /**
   * constructs the Oval given name.
   *
   * @param name the name of the oval
   */
  public Oval(String name) {
    super(name);
    this.name = name;
    this.color = new RGBColor(0, 0, 0);
    this.w = 0;
    this.h = 0;
    this.pos = new Posn(0, 0);
    this.keyFrames = new ArrayList<>();
  }

  /**
   * the constructor to construct the oval.
   *
   * @param name     the name of Model.Oval
   * @param w        the vertical radius of the Model.Oval
   * @param h        the horizontal radius of Model.Oval
   * @param color    the color of the Model.Oval
   * @param pos      the position of the Model.Oval
   * @param keyFrame the keyFrame of the Model.Oval
   */
  public Oval(String name, double w, double h, RGBColor color, Posn pos, List<KeyFrame> keyFrame) {
    super(name, w, h, color, pos, keyFrame);
    if (w < 0.0001 || h < 0.0001) {
      throw new IllegalArgumentException("Invalid radius to create the oval");
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
  public int hashCode() {
    int result = 2;
    result += 3 * this.name.hashCode();
    result += 5 * Double.hashCode(this.w);
    result += 7 * Double.hashCode(this.h);
    result += 11 * this.pos.hashCode();
    result += 13 * this.keyFrames.hashCode();
    result += 17 * this.color.hashCode();
    return result;
  }

  @Override
  public String getShapeAsString() {
    return "oval";
  }

  @Override
  public IShape getShape() {
    return new Oval("", 0.0001, 0.0001, new RGBColor(0, 0, 0),
        new Posn(0, 0), new ArrayList<>());
  }
}


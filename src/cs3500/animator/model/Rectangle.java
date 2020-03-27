package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Model.Rectangle in the animation.
 */
public class Rectangle extends AShape {

  /**
   * constructs the rectangle with the given name.
   *
   * @param name the name of the rectangle
   */
  public Rectangle(String name) {
    super(name);
    this.name = name;
    this.color = new RGBColor(0, 0, 0);
    this.w = 0;
    this.h = 0;
    this.pos = new Posn(0, 0);
    this.keyFrames = new ArrayList<>();
  }

  /**
   * the constructor to construct the rectangle.
   *
   * @param name     the name of the Model.Rectangle
   * @param w        the width of the Model.Rectangle
   * @param h        the height of the Model.Rectangle
   * @param color    the color of the Model.Rectangle
   * @param pos      the position of the Model.Rectangle
   * @param keyFrame the keyFrame of the Model.Rectangle
   */
  public Rectangle(String name, double w, double h, RGBColor color, Posn pos,
      List<KeyFrame> keyFrame) {
    super(name, w, h, color, pos, keyFrame);
    if (w < 0.0001 || h < 0.0001) {
      throw new IllegalArgumentException("Invalid height and width to create a valid rectangle");
    }
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
    return "rectangle";
  }
}
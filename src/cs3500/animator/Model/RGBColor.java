package cs3500.animator.Model;

/**
 * A class Model.RGBColor to represent the color of the shape in animation.
 */
public class RGBColor {

  private int red;
  private int green;
  private int blue;

  /**
   * to represent the Model.RGBColor's constructor.
   *
   * @param red   the int format of the rgb color of red
   * @param green the int format of the rgb color of green
   * @param blue  the int format of the rgb color of blue
   */
  public RGBColor(int red, int green, int blue) {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("RGB color can not has negative elements");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * In order to output the RGBcolor in the string format.
   *
   * @return the string format of the color
   */
  public String asString() {
    return this.red + " " + this.green + " " + this.blue;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof RGBColor)) {
      return false;
    } else {
      return (this.blue == ((RGBColor) other).blue
          && this.red == ((RGBColor) other).red
          && this.green == ((RGBColor) other).green);
    }
  }

  @Override
  public int hashCode() {
    int result = 2;
    result += 3 * this.red;
    result += 5 * this.green;
    result += 7 * this.blue;
    return result;
  }
}
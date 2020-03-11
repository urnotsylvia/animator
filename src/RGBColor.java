/**
 * A class RGBColor to represent the color of the shape in animation.
 */
public class RGBColor {

  private int red;
  private int green;
  private int blue;

  /**
   * to represent the RGBColor's constructor.
   *
   * @param red   the int format of the rgb color of red
   * @param green the int format of the rgb color of green
   * @param blue  the int format of the rgb color of blue
   */
  public RGBColor(int red, int green, int blue) {
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
}

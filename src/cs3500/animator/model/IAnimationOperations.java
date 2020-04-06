package cs3500.animator.model;


/**
 * This is the interface of the Model.IAnimationOperations, that representing all the functions in
 * the animator.
 */
public interface IAnimationOperations extends IReadonlyAnimationOperations {

  /**
   * Add the shape to the list of keyFrame.
   *
   * @param name the name of the new shape
   * @param type the type of the shape
   */
  void add(String name, String type);

  /**
   * Remove the shape from the list of shape.
   *
   * @param name represent the name of the shape that should be removed
   */
  void remove(String name);

  /**
   * In order to get the string format of the shape's motion that starts at the given time.
   *
   * @param which represent the name of the shape
   * @param when  the motion that user want to get start from which tick
   * @return the string format of the motion
   */
  String motionToString(String which, int when);

  /**
   * add the keyFrame with given values to the shape of given name.
   *
   * @param name the name of the shape that keyFrame need to be added to
   * @param t    time
   * @param x    x
   * @param y    y
   * @param w    width
   * @param h    height
   * @param r    red in RGBColor
   * @param g    green in RGBColor
   * @param b    blue in RGBColor
   */
  void addKeyframe(String name, int t, int x, int y,
      int w, int h, int r, int g, int b);

  /**
   * specify the bound with given attributes values.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   * @throws IllegalArgumentException if any value of the attribute is negative
   */
  void setBounds(int x, int y, int width, int height);

  /**
   * gets the maximum tick the animation needs
   *
   * @return the maximum tick
   */
  int maxTick();
}

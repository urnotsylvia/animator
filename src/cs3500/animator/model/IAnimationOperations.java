package cs3500.animator.model;

import java.util.List;

/**
 * This is the interface of the Model.IAnimationOperations, that representing all the functions in
 * the animator.
 */
public interface IAnimationOperations {

  /**
   * Add the shape to the list of keyFrame.
   *
   * @param name the name of the new shape
   * @param type the type of the shape
   */
  void add(String name, String type);

  /**
   * get the attribute that is part of the bound according to the String given.
   *
   * @param i the String that represents the attribute name, can be one of x, y, w, h
   * @return the value of the attribute
   */
  int getBound(String i);

  /**
   *
   * @return
   */
  List<Integer> getBoundAsList();

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
   * return the list of shapes of the model.
   *
   * @return list of shapes
   */
  List<IShape> getShapes();

  /**
   * Return the present state of the game as a string.
   *
   * @param when to represent the tick in order to refer to the motion
   * @return the list of shape
   * @throws IllegalArgumentException if there is a conflicting/overlapping motion
   */
  List<IShape> getState(int when);

  /**
   * get a list of shape that has each key associated with a shape.
   *
   * @return a list of shapes with the active keyFrame
   */
  List<IShape> getShapesWithAllKeys();

  /**
   * add the keyFrame with given values to the shape of given name.
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
   * return the bound information as a string.
   *
   * @return string that represents the 4 attributes of the bounding
   */
  String getBoundsAsString();
}

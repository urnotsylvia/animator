package cs3500.animator.Model;

import java.util.List;

/**
 * This is the interface of the Model.IAnimationOperations, that representing all the functions in the
 * animator.
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
   *
   * @param name
   * @param t
   * @param x
   * @param y
   * @param w
   * @param h
   * @param r
   * @param g
   * @param b
   */
  public void addKeyframe(String name, int t, int x, int y,
      int w, int h, int r, int g, int b);
}

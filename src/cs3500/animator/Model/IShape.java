package cs3500.animator.Model;


import java.util.List;

/**
 * This is the interface for shape that takes part in the animation.
 */
public interface IShape {

  /**
   * Return the color of this shape.
   *
   * @return Color
   */
  RGBColor getColor();

  /**
   * drops the keyFrame based on the given time
   *
   * @param time the time of the keyFrame to be drop
   */
  void dropKeyFrame(int time);

  /**
   * Returns the name of the shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * @return the corresponding shape based on its type as a String
   */
  String getShapeAsString();

  /**
   * return the corresponding shape based on the shape type.
   *
   * @return the shape type
   */
  IShape getShape();

  /**
   * Return the position of the shape.
   *
   * @return the position of the shape
   */
  Posn getPos();

  /**
   * add the new keyFrame to the list of keyFrames of the shape.
   *
   * @param newKey represent the new keyFrame
   * @throws IllegalArgumentException if the tick has already been taken in th e;ist of keyFrames
   */
  void addKeyFrame(KeyFrame newKey);

  /**
   * to get the keyFrames of the shape.
   *
   * @return the list of keyFrame
   */
  List<KeyFrame> getKeyFrames();

  /**
   * Get the w or h according to the given require.
   *
   * @param wH "w" indicates to get W, "h" indicates to get H
   * @return double that represents w or h
   */
  double getWOrH(String wH);

  /**
   * change the position to the given position.
   *
   * @param newPos represent the new position to change
   */
  void changePos(Posn newPos);

  /**
   * changes the width or length or height of the shape.
   *
   * @param wH        the width and the height of the shape
   * @param newLength represent how the length should change according to the newLength
   */
  void setLength(String wH, double newLength);

  /**
   * changes the color of the shape.
   *
   * @param newColor represent the color of the shape should change to
   */
  void changeColor(RGBColor newColor);

  /**
   * change the name of the shape to the given String.
   *
   * @param name the new name to be change to
   */
  void setName(String name);
}

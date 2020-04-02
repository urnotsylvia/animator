package cs3500.animator.model;

import java.util.List;

public interface IReadonlyAnimationOperations {
  /**
   * get the attribute that is part of the bound according to the String given.
   *
   * @param i the String that represents the attribute name, can be one of x, y, w, h
   * @return the value of the attribute
   */
  int getBound(String i);

  /**
   * returns the bound information as a list of integer.
   * @return a list of integer that represents the bounds
   */
  List<Integer> getBoundAsList();


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
   * return the bound information as a string.
   *
   * @return string that represents the 4 attributes of the bounding
   */
  String getBoundsAsString();
}

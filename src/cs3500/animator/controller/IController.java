package cs3500.animator.controller;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * the interface of the controller that start the animation.
 */
public interface IController extends ActionListener {

  /**
   * start the animation.
   */
  void playAnimation();

  /**
   * gets the current time.
   */
  int getTime();

  /**
   * change the loop to the opposite.
   */
  void changeLoop();

  /**
   * pauses the animation.
   */
  void pauseAnimation();

  /**
   * resumes the animation.
   */
  void resumeAnimation();

  /**
   * changes the speed during the animation.
   *
   * @param speed the speed to change to
   */
  void changeSpeed(int speed);

  /**
   * adds a new keyFrame to the model based on the input from user and the shape name chosen.
   *
   * @param name   the name of the shape to add the keyFrame
   * @param values the values for the keyFrame as a list of integer
   */
  void addKeyFrame(String name, List<Integer> values);
}

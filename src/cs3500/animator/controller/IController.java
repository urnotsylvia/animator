package cs3500.animator.controller;

import cs3500.animator.model.IAnimationOperations;
import java.awt.event.ActionListener;

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
   * adds a new keyFrame to the model.
   */
  void addKeyFrame();
}

package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IShape;
import java.util.List;

/**
 * the view interface that show the animation.
 */
public interface IView {

  /**
   * display the animation.
   */
  void showAnimation();

  /**
   *
   * @param listener
   */
  void addActionListener(IController listener);

  /**
   * makes the view visible.
   */
  void makeVisible();

  /**
   * refreshes the view.
   */
  void refresh();
}

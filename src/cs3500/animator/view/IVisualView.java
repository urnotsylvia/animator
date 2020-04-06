package cs3500.animator.view;

import cs3500.animator.controller.IController;

/**
 * the interface that visualize the animation.
 */
public interface IVisualView extends IView {

  /**
   * adds the actionListener to the view.
   *
   * @param listener controller as listener
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

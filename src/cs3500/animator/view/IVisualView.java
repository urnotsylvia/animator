package cs3500.animator.view;

import cs3500.animator.controller.IController;

public interface IVisualView extends IView {
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

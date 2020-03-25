package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;

public interface IView {

  /**
   * Make the view visible.
   */
  void makeVisible();

  /**
   * Return the viewType from the view.
   * @return the viewType
   */
  ViewType getViewType();


  void showAnimation(IAnimationOperations m);
}

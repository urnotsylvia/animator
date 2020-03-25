package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;

public interface IView {

  void makeVisible();
  ViewType getViewType();

  void playAnimation(IAnimationOperations m);
}

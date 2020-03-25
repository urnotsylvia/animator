package cs3500.animator.Controller;

import cs3500.animator.Model.IAnimationOperations;

public interface IController {
  void playAnimation(IAnimationOperations m);
  void factory();
}

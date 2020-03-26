package cs3500.animator.Controller;

import cs3500.animator.Model.IAnimationOperations;
import cs3500.animator.view.IView;

public class AnimationController implements IController {
private IAnimationOperations model;
private IView view;

public AnimationController(IAnimationOperations model, IView view) {
  this.model = model;
  this.view = view;
}

@Override
  public void playAnimation(IAnimationOperations m) {
  this.model = m;
  this.view.makeVisible();
  this.view.showAnimation();
}
}

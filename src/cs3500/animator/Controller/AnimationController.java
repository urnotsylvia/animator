package cs3500.animator.Controller;

import cs3500.animator.Model.IAnimationOperations;
import cs3500.animator.view.IView;

/**
 * the Controller that start the animation
 */
public class AnimationController implements IController {

  private IAnimationOperations model;
  private IView view;

  /**
   * constructs the controller
   *
   * @param model the model that stores all the data of the animation
   * @param view  the view that shows the animation
   */
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

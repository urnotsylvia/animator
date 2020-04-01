package cs3500.animator.controller;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.view.IView;

/**
 * the Controller that start the animation.
 */
public class AnimationController implements IController {

  private IAnimationOperations model;
  private IView view;
  //loop
  //speed
  //time int(or Timer)

  /**
   * constructs the controller.
   *
   * @param model the model that stores all the data of the animation
   * @param view  the view that shows the animation
   */
  public AnimationController(IAnimationOperations model, IView view) {
    this.model = model;
    this.view = view;

  }

  @Override
  public void playAnimation() {
    this.view.showAnimation(model.getShapes(), model.getBoundAsList());
  }
}

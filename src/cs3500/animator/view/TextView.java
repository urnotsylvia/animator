package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;

/**
 * The View of the Animation to produce a textual description of the animation
 * that is compliant with the popular SVG (Scalable Vector Graphics) file format.
 */
public class TextView extends AView {


  public TextView(IAnimationOperations model) {
    super(model);
  }

  @Override
  public ViewType getViewType() {
    return ViewType.TEXT;
  }

  @Override
  public void playAnimation(IAnimationOperations m) {

  }
}

package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;

public class SVGView extends AView {

  public SVGView(IAnimationOperations model) {
    super(model);
  }
  //use


  @Override
  public ViewType getViewType() {
    return ViewType.SVG;
  }

  @Override
  public void playAnimation(IAnimationOperations m) {

  }
}

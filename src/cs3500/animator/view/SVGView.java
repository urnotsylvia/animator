package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;
import javax.swing.JFrame;

public class SVGView extends AView {

  public SVGView (IAnimationOperations model, int speed, Appendable output) {
    super(model, speed, output);
    this.speed = speed;
    this.output = output;

    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new AnimationPanel(model);
    this.add(panel);
  }
  //use


  @Override
  public ViewType getViewType() {
    return ViewType.SVG;
  }

  @Override
  public void showAnimation(IAnimationOperations m) {

  }
}

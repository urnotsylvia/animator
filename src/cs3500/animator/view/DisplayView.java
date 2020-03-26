package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;
import javax.swing.JFrame;
import javax.swing.Timer;

public class DisplayView extends AView {


  public DisplayView(IAnimationOperations model, int speed, Appendable output) {
    super(model, speed, output);
    this.speed = speed;
    this.output = output;

    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new AnimationPanel(model, speed);
    this.add(panel);
  }

  //Timer //swing
  @Override
  public ViewType getViewType() {
    return ViewType.DISPLAY;
  }

  @Override
  public void showAnimation() {
    this.repaint();
  }
}


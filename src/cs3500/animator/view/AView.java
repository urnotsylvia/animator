package cs3500.animator.view;

import cs3500.animator.model.IAnimationOperations;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * the view that shows the aimation in different types.
 */
public abstract class AView extends JFrame implements IView {

  protected AnimationPanel panel;
  protected Timer timer;
  protected Appendable output;
  protected int speed;
  protected IAnimationOperations model;

  /**
   * constructs the view given model, speed and the appendable to output.
   *
   * @param model  the animation model
   * @param speed  the speed
   * @param output the output file
   */
  public AView(IAnimationOperations model, int speed, Appendable output) {
    super("Animation");
    this.model = model;
    this.speed = speed;
    this.output = output;

    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new AnimationPanel(model, speed);
    this.add(panel);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
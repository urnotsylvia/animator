package cs3500.animator.view;
import cs3500.animator.Model.IAnimationOperations;
import javax.swing.JFrame;
import javax.swing.Timer;

public abstract class AView extends JFrame implements IView {
  protected AnimationPanel panel;
  protected Timer timer;
  protected Appendable output;
  protected int speed;
  protected IAnimationOperations model;

  public AView (IAnimationOperations model, int speed, Appendable output) {
    super("Animation");
    this.model = model;
    this.speed = speed;
    this.output = output;

    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new AnimationPanel(model);
    this.add(panel);
  }


  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}

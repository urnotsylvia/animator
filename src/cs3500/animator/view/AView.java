package cs3500.animator.view;
import cs3500.animator.Controller.AnimationController;
import cs3500.animator.Model.IAnimationOperations;
import javax.swing.JFrame;

public abstract class AView extends JFrame implements IView {
  private AnimationPanel panel;

  public AView (IAnimationOperations model) {
    super("Animation");

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

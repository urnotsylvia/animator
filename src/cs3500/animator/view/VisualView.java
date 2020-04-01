package cs3500.animator.view;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.model.IShape;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * the view that display the animation to visualize it.
 */
public class VisualView extends JFrame implements IView {

  /**
   * constructs the view given model, speed and the appendable to output.
   *
   * @param model the animation model
   * @param speed the speed
   */
  public VisualView(IAnimationOperations model, int speed) {

    setSize(model.getBound("w"), model.getBound("h"));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    AnimationPanel panel = new AnimationPanel(model, speed);
    panel.getPreferredSize();
    JScrollPane scrollPane = new JScrollPane(panel);

    this.add(scrollPane);

    this.setSize(model.getBound("w"), model.getBound("h"));

    this.setLocation(model.getBound("x"), model.getBound("y"));

    this.makeVisible();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showAnimation(List<IShape> shapes, List<Integer> bounds) {
    this.repaint();
  }
}


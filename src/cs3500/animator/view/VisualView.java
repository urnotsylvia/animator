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
  private int speed;
  /**
   * constructs the view given model, speed and the appendable to output.
   *
   * @param speed the speed
   */
  public VisualView(int speed) {
    this.speed = speed;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showAnimation(List<IShape> shapes, List<Integer> bounds) {


    setSize(bounds.get(2), bounds.get(3));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    AnimationPanel panel = new AnimationPanel(shapes, speed);
    panel.getPreferredSize();
    JScrollPane scrollPane = new JScrollPane(panel);

    this.add(scrollPane);

    this.setLocation(bounds.get(0), bounds.get(1));

    this.makeVisible();

    this.repaint();

  }
}


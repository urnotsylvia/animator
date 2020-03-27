package cs3500.animator.view;

import cs3500.animator.model.IAnimationOperations;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * the view that display the animation to visualize it.
 */
public class DisplayView extends AView {

  /**
   * constructs the view given model, speed and the appendable to output.
   *
   * @param model  the animation model
   * @param speed  the speed
   * @param output the output file
   */
  public DisplayView(IAnimationOperations model, int speed, Appendable output) {
    super(model, speed, output);
    this.speed = speed;
    this.output = output;

    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new AnimationPanel(model, speed);
    panel.getPreferredSize();
    JScrollPane scrollPane = new JScrollPane(panel);

    this.add(scrollPane);

    this.setSize(model.getBound("w"), model.getBound("h"));

    this.setLocation(model.getBound("x"), model.getBound("y"));
  }

  @Override
  public void showAnimation() {
    this.repaint();
  }
}


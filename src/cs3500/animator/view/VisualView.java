package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IReadonlyAnimationOperations;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * the view that display the animation to visualize it.
 */
public class VisualView extends JFrame implements IVisualView {

  private IReadonlyAnimationOperations model;
  private AnimationPanel panel;

  public VisualView(IReadonlyAnimationOperations model) {
    this.model = model;
    this.panel = new AnimationPanel();
    this.makeVisible();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void addActionListener(IController listener) {
    panel = new AnimationPanel(model, listener);
  }

  @Override
  public void showAnimation() {
    setSize(model.getBound("w"), model.getBound("h"));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel.setPreferredSize(new Dimension(model.getBound("w"), model.getBound("h")));
    panel.getPreferredSize();
    JScrollPane scrollPane = new JScrollPane(panel);

    this.add(scrollPane);

    this.setLocation(model.getBound("x"), model.getBound("y"));

    this.makeVisible();
  }
}


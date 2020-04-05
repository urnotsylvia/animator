package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.model.IReadonlyAnimationOperations;
import cs3500.animator.model.IShape;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.imageio.event.IIOReadProgressListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * the view that display the animation to visualize it.
 */
public class VisualView extends JFrame implements IVisualView {

  private IReadonlyAnimationOperations model;
  private int speed;
  private AnimationPanel panel;

  /**
   * constructs the view given model, speed and the appendable to output.
   *
   * @param speed the speed
   */
  public VisualView(IReadonlyAnimationOperations model, int speed) {
    this.model = model;
    this.speed = speed;
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
    panel = new AnimationPanel(model.getShapes(), speed, listener);
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


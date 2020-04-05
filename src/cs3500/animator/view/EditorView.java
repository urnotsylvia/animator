package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.IReadonlyAnimationOperations;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * A class that represent the editor view that allow the animation to play more than once, and add,
 * remove, and modify items from the animation.
 */
public class EditorView extends JFrame implements IView {

  private IReadonlyAnimationOperations model;
  private AnimationPanel panel1;
  private EditorPanel panel2;
  private int speed;


  public EditorView(IReadonlyAnimationOperations model,
      int speed) {
    this.model = model;
    this.speed = speed;
    panel1 = new AnimationPanel();
    panel2 = new EditorPanel();

    this.setLayout(new BorderLayout());

    this.add(panel1, BorderLayout.NORTH);
    this.add(panel2, BorderLayout.SOUTH);

    this.makeVisible();
  }


  @Override
  public void showAnimation() {
    setSize(model.getBound("w"), model.getBound("h") + 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel1.setPreferredSize(new Dimension(model.getBound("w"), model.getBound("h")));
    panel1.getPreferredSize();
    panel2.setPreferredSize(new Dimension(model.getBound("w"), 100));
    panel2.setLayout(new FlowLayout());
    panel2.getPreferredSize();
    JScrollPane scrollPane = new JScrollPane(panel1);

    this.add(scrollPane);

    this.setLocation(model.getBound("x"), model.getBound("y"));

    this.makeVisible();
  }

  @Override
  public void addActionListener(IController listener) {
    panel1 = new AnimationPanel(model.getShapes(), speed, listener);
    panel2.addActionListener(listener);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }
}

package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import javax.swing.JPanel;

public class AnimationPanel extends JPanel {
  private IAnimationOperations model;
  private Timer timer;

  public AnimationPanel(IAnimationOperations model) {
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    String result = "";

  }
}

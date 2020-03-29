package cs3500.animator.view;

import cs3500.animator.model.IAnimationOperations;
import cs3500.animator.model.IShape;
import cs3500.animator.model.KeyFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * draw the animation each tick.
 */
public class AnimationPanel extends JPanel implements ActionListener {

  private IAnimationOperations model;
  private int curTime = 1;

  /**
   * constructs the panel that given model and speed.
   *
   * @param model the animation model
   * @param speed the rate that specifies how many tick per ms
   */
  public AnimationPanel(IAnimationOperations model, int speed) {
    this.model = model;
    Timer timer = new Timer(500 / speed, this);
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    //Perform a task
    curTime++;
    this.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    List<IShape> shapes = model.getShapesWithAllKeys();
    for (int i = 0; i < shapes.size() - 1; i++) {
      //find the corresponding tick
      KeyFrame curKey = shapes.get(i).getKeyFrames().get(0);
      if (curKey.getTime() == curTime) {
        g2.setColor(new Color(curKey.getColor().getRGB("r"),
            curKey.getColor().getRGB("g"),
            curKey.getColor().getRGB("b")));
        switch (shapes.get(i).getShapeAsString()) {
          case "rectangle":
            g2.fillRect(curKey.getX(), curKey.getY(),
                curKey.getW(), curKey.getH());
            break;
          case "oval":
          case "ellipse":
            g2.fillOval((int) (curKey.getX() * 0.5), (int) (curKey.getY() * 0.5),
                curKey.getW(), curKey.getH());
            break;
          default:
            throw new IllegalArgumentException("no such shape");
        }
      } else {
        int tA = curKey.getTime();
        KeyFrame nextKey = shapes.get(i + 1).getKeyFrames().get(0);
        int tB = nextKey.getTime();
        if (curTime > tA
            && curTime < tB) {
          //tweening color
          g2.setColor(new Color(((getTweening(curKey.getColor().getRGB("r"),
              nextKey.getColor().getRGB("r"), tA, tB, curTime))),
              getTweening(curKey.getColor().getRGB("g"),
                  nextKey.getColor().getRGB("g"), tA, tB, curTime),
              getTweening(curKey.getColor().getRGB("b"),
                  nextKey.getColor().getRGB("b"), tA, tB, curTime)));

          switch (shapes.get(i).getShapeAsString()) {
            case "rectangle":
              g2.fillRect(getTweening(curKey.getX(), nextKey.getX(), tA, tB, curTime),
                  getTweening(curKey.getY(), nextKey.getY(), tA, tB, curTime),
                  getTweening(curKey.getW(), nextKey.getW(), tA, tB, curTime),
                  getTweening(curKey.getH(), nextKey.getH(), tA, tB, curTime));
              break;
            case "oval":
            case "ellipse":
              g2.fillOval(getTweening((int) (curKey.getX() * 0.5),
                  (int) (nextKey.getX() * 0.5), tA, tB, curTime),
                  getTweening((int) (curKey.getY() * 0.5),
                      (int) (nextKey.getY() * 0.5), tA, tB, curTime),
                  getTweening(curKey.getW(), nextKey.getW(), tA, tB, curTime),
                  getTweening(curKey.getH(), nextKey.getH(), tA, tB, curTime));
              break;
            default:
              throw new IllegalArgumentException("no such shape");
          }
        }
      }
    }
  }

  /**
   * calculated the value if there is no existing keyFrame that associates with it.
   *
   * @param n1 n1
   * @param n2 n2
   * @param t1 time 1
   * @param t2 time 2
   * @return int that represents the value
   */
  private int getTweening(int n1, int n2, int t1, int t2, int curTime) {
    double rate1 = (t2 - curTime + 0.0) / (t2 - t1);
    double rate2 = (curTime + 0.0 - t1) / (t2 - t1);
    return (int) (n1 * rate1 + n2 * rate2);
  }
}

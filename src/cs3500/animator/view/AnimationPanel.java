package cs3500.animator.view;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IReadonlyAnimationOperations;
import cs3500.animator.model.IShape;
import cs3500.animator.model.KeyFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * draw the animation each tick.
 */
public class AnimationPanel extends JPanel {

  private IController controller;
  private IReadonlyAnimationOperations model;

  /**
   * constructs the panel that takes in the model and controller.
   *
   * @param model      the readonly model that cannot be mutated
   * @param controller the controller as a action listener
   */
  public AnimationPanel(IReadonlyAnimationOperations model, IController controller) {
    this.controller = controller;
    this.model = model;
  }

  /**
   * constructor that initialize the model.
   */
  public AnimationPanel() {
    this.model = new AnimationModel(new ArrayList<>());
  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    List<IShape> shapesWithAllKeys = new ArrayList<>();
    for (IShape s : model.getShapes()) {
      shapesWithAllKeys.addAll(s.getShapesWithAllKeys());
    }
    for (int i = 0; i < shapesWithAllKeys.size() - 1; i++) {
      //find the corresponding tick
      KeyFrame curKey = shapesWithAllKeys.get(i).getKeyFrames().get(0);
      if (curKey.getTime() == controller.getTime()) {
        g2.setColor(new Color(curKey.getColor().getRGB("r"),
            curKey.getColor().getRGB("g"),
            curKey.getColor().getRGB("b")));
        switch (shapesWithAllKeys.get(i).getShapeAsString()) {
          case "rectangle":
            g2.fillRect(curKey.getX(), curKey.getY(),
                curKey.getW(), curKey.getH());
            break;
          case "oval":
          case "ellipse":
            g2.fillOval(curKey.getX(), (curKey.getY()),
                (int) (curKey.getW()), (int) (curKey.getH()));
            break;
          default:
            throw new IllegalArgumentException("no such shape");
        }
      } else {
        int tA = curKey.getTime();
        KeyFrame nextKey = shapesWithAllKeys.get(i + 1).getKeyFrames().get(0);
        int tB = nextKey.getTime();
        if (controller.getTime() > tA
            && controller.getTime() < tB) {
          //tweening color
          g2.setColor(new Color(((getTweening(curKey.getColor().getRGB("r"),
              nextKey.getColor().getRGB("r"), tA, tB, controller.getTime()))),
              getTweening(curKey.getColor().getRGB("g"),
                  nextKey.getColor().getRGB("g"), tA, tB, controller.getTime()),
              getTweening(curKey.getColor().getRGB("b"),
                  nextKey.getColor().getRGB("b"), tA, tB, controller.getTime())));

          switch (shapesWithAllKeys.get(i).getShapeAsString()) {
            case "rectangle":
              g2.fillRect(getTweening(curKey.getX(), nextKey.getX(), tA, tB, controller.getTime()),
                  getTweening(curKey.getY(), nextKey.getY(), tA, tB, controller.getTime()),
                  getTweening(curKey.getW(), nextKey.getW(), tA, tB, controller.getTime()),
                  getTweening(curKey.getH(), nextKey.getH(), tA, tB, controller.getTime()));
              break;
            case "oval":
            case "ellipse":
              g2.fillOval(getTweening((curKey.getX()),
                  (nextKey.getX()), tA, tB, controller.getTime()),
                  getTweening((curKey.getY()),
                      (nextKey.getY()), tA, tB, controller.getTime()),
                  getTweening((int) (curKey.getW()), (int) (nextKey.getW()), tA, tB,
                      controller.getTime()),
                  getTweening((int) (curKey.getH()), (int) (nextKey.getH()), tA, tB,
                      controller.getTime()));
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
    return (int) Math.round(n1 * rate1 + n2 * rate2);
  }
}


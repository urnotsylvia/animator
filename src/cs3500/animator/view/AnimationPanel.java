package cs3500.animator.view;

import cs3500.animator.Model.IAnimationOperations;
import cs3500.animator.Model.IShape;
import cs3500.animator.Model.KeyFrame;
import cs3500.animator.Model.Oval;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPanel extends JPanel {
  private IAnimationOperations model;
  private Timer timer;
  private int curTime = 1;

  public AnimationPanel(IAnimationOperations model, int speed) {
    this.model = model;
    //this.timer = new Timer(1000/speed, taskPerformer).start();
  }

/*
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        //...Perform a task...
        curTime ++;
        List<IShape> curShapes = model.getState(curTime);
        for (IShape s: curShapes) {
          for (int i = 0; i < s.getKeyFrames().size(); i++) {
            //find the corresponding tick
            if (s.getKeyFrames().get(i).getTime() == curTime) {
              switch (s.getShapeAsString()) {
                case "Rectangle":
                  //draw the shape with all the values
                  g2.draw(new Rectangle());
                  break;
                case "Oval":
                  g2.draw(new Oval());
                  break;
                default:
                  throw new IllegalArgumentException("no such shape");
              }
              else {
                //find the previous keyFrame and the next keyFrame and use the math to calculate all thevalues
                if (curTime > s.getKeyFrames().get(i - 1).getTime()
                && curTime < s.getKeyFrames().get(i).getTime()) {
                //tweening
                }
              }
            }
          }
        }
      }
    };



    //get the

  }
  */
}
